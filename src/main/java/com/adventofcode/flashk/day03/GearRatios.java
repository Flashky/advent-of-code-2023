package com.adventofcode.flashk.day03;

import com.adventofcode.flashk.common.Grid;
import com.adventofcode.flashk.common.GridTile;
import com.adventofcode.flashk.common.GridUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class GearRatios {

    private static final char EMPTY = '.';

    // Initial data structure
    private final Grid<Character> engine;

    // Calculated symbol information
    private final Map<Symbol,Symbol> symbols = new HashMap<>();

    public GearRatios(char[][] input) {

        engine = GridUtil.asGrid(input);

        processGrid();

    }

    public int solveA() {
        return symbols.keySet().stream().map(Symbol::sumPartNumbers).reduce(0, Integer::sum);
    }

    public int solveB() {
        return symbols.keySet().stream().distinct().map(Symbol::gearRatio).reduce(0, Integer::sum);
    }

    private void processGrid() {
        for(int row = 0; row < engine.rows(); row++) {
            int col = 0;
            while(col < engine.cols()) {
                if(Character.isDigit(engine.get(row,col))) {
                    col = processNumber(row, col);
                } else {
                    col++;
                }
            }
        }
    }

    private int processNumber(int row, int col) {
        
        StringBuilder number = new StringBuilder();
        int currentCol = col;

        // Find number and symbol if present
        Optional<Symbol> symbol = Optional.empty();
        while(currentCol < engine.cols() && Character.isDigit(engine.get(row, currentCol))) {
            number.append(engine.get(row,currentCol));

            if(symbol.isEmpty()) {
                symbol = findSymbol(row, currentCol);
            }

            currentCol++;
        }

        // If current number has a symbol, update data structures
        if(symbol.isPresent()) {
            int calculatedPartNumber = Integer.parseInt(number.toString());
            saveOrUpdateSymbol(symbol.get(), calculatedPartNumber);
        }

        return currentCol;

    }

    private void saveOrUpdateSymbol(Symbol symbol, int calculatedPartNumber) {
        symbols.getOrDefault(symbol,symbol).addAdjacentPartNumber(calculatedPartNumber);
        symbols.putIfAbsent(symbol,symbol);
    }

    private Optional<Symbol> findSymbol(int row, int col) {
        Set<GridTile<Character>> adjacents = engine.getAdjacentTiles(row, col, true);
        return adjacents.stream()
                .filter(t -> this.isSymbol(t.getValue()))
                .findFirst()
                .map(this::createSymbol);
    }

    private boolean isSymbol(Character value) {
        return  !Character.isDigit(value) && value != EMPTY;
    }

    private Symbol createSymbol(GridTile<Character> tile) {
        return new Symbol(tile.getRow(), tile.getCol(), tile.getValue());
    }
}
