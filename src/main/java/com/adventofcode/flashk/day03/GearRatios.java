package com.adventofcode.flashk.day03;

import com.adventofcode.flashk.common.GridUtil;
import com.adventofcode.flashk.common.Vector2;

import java.util.*;

public class GearRatios {

    private static final char EMPTY = '.';

    // Initial data structure
    private final int rows;
    private final int cols;
    private final char[][] engine;

    // Calculated symbol information
    private final List<Symbol> symbols = new ArrayList<>();
    private final Map<Vector2, Symbol> symbolPositions = new HashMap<>();

    public GearRatios(char[][] input) {

        engine = input;
        rows = engine.length;
        cols = engine[0].length;

        processGrid();

    }

    public int solveA() {
        return symbols.stream().map(Symbol::sumPartNumbers).reduce(0, Integer::sum);
    }

    public int solveB() {
        return symbols.stream().map(Symbol::gearRatio).reduce(0, Integer::sum);
    }

    private void processGrid() {
        for(int row = 0; row < rows; row++) {
            int col = 0;
            while(col < cols) {
                if(Character.isDigit(engine[row][col])) {
                    col = processNumber(row, col);
                } else {
                    col++;
                }
            }
        }
    }

    private int processNumber(int row, int col) {
        
        StringBuilder number = new StringBuilder();

        // Find number and symbol if present
        Optional<Symbol> symbol = Optional.empty();
        while(col < cols && Character.isDigit(engine[row][col])) {
            number.append(engine[row][col]);

            if(symbol.isEmpty()) {
                symbol = findSymbol(row, col);
            }

            col++;
        }

        // If current number has a symbol, update data structures
        if(symbol.isPresent()) {
            int calculatedPartNumber = Integer.parseInt(number.toString());
            saveOrUpdateSymbol(symbol.get(), calculatedPartNumber);
        }

        return col;

    }

    private void saveOrUpdateSymbol(Symbol symbol, int calculatedPartNumber) {
        Vector2 symbolPos = symbol.getPos();

        // Symbol has been previously added
        if(symbolPositions.containsKey(symbolPos)) {
            symbolPositions.get(symbolPos).addAdjacentPartNumber(calculatedPartNumber);
        } else {
            symbol.addAdjacentPartNumber(calculatedPartNumber);
            symbolPositions.put(symbolPos, symbol);
            symbols.add(symbol);
        }
    }

    private Optional<Symbol> findSymbol(int row, int col) {
        List<Vector2> adjacents = GridUtil.getAdjacentsIncludingDiagonals(engine, row, col);
        return adjacents.stream().filter(this::isSymbol).findFirst().map(this::createSymbol);
    }

    private boolean isSymbol(Vector2 pos) {
        char value = engine[pos.getY()][pos.getX()];
        return  !Character.isDigit(value) && value != EMPTY;
    }

    private Symbol createSymbol(Vector2 pos) {
        char value = engine[pos.getY()][pos.getX()];
        return new Symbol(value, pos);
    }
}
