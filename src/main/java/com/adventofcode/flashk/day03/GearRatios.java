package com.adventofcode.flashk.day03;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GearRatios {

    private static final char EMPTY = '.';
    private static final char GEAR = '*';

    private final int rows;
    private final int cols;

    private char[][] engine;

    private Map<Vector2, MutablePair<Integer, Integer>> gearAdjacency = new HashMap<>();

    public GearRatios(List<String> inputs) {

        rows = inputs.size();
        cols = inputs.get(0).length();
        engine = new char[rows][cols];

        int i = 0;
        for(String input : inputs) {
            engine[i++] = input.toCharArray();
        }
    }

    public long solveA() {

        long result = 0;

        for(int row = 0; row < rows; row++) {

            int col = 0;
            while(col < cols) {

                if (Character.isDigit(engine[row][col])) {
                    // Se ha encontrado el inicio de un nuevo número.
                    StringBuilder number = new StringBuilder();
                    boolean hasAdjacentSymbols = false;
                    while(col < cols && Character.isDigit(engine[row][col])) {
                        number.append(engine[row][col]);
                        hasAdjacentSymbols |= hasAdjacentSymbols(row,col++);
                    }

                    if(hasAdjacentSymbols) {
                        result += Integer.parseInt(number.toString());
                    }

                } else {
                    col++;
                }
            }
        }

        return result;

    }

    public long solveB() {
        long result = 0;

        for(int row = 0; row < rows; row++) {

            int col = 0;
            while(col < cols) {

                if (Character.isDigit(engine[row][col])) {
                    // Se ha encontrado el inicio de un nuevo número.
                    StringBuilder number = new StringBuilder();
                    boolean hasAdjacentSymbols = false;
                    Optional<Vector2> gear = Optional.empty();
                    while(col < cols && Character.isDigit(engine[row][col])) {
                        number.append(engine[row][col]);
                        gear = getGearIfPresent(row, col, gear);
                        if(gear.isPresent()) {
                            MutablePair<Integer,Integer> pair = MutablePair.of(null, null);
                            gearAdjacency.putIfAbsent(gear.get(), pair);
                        }
                        hasAdjacentSymbols |= hasAdjacentSymbols(row,col++);

                    }

                    if(hasAdjacentSymbols) {
                        int calculatedNumber = Integer.parseInt(number.toString());
                        result += calculatedNumber;
                        if(gear.isPresent()) {
                            if(gearAdjacency.containsKey(gear.get())) {
                                MutablePair<Integer,Integer> pair = gearAdjacency.get(gear.get());
                                if(pair.getLeft() == null) {
                                    pair.setLeft(calculatedNumber);
                                } else if(pair.getRight() == null) {
                                    pair.setRight(calculatedNumber);
                                }
                            }
                        }
                    }

                } else {
                    col++;
                }
            }
        }


        return gearAdjacency.values().stream()
                .filter(g -> g.getRight() != null)
                .map(g -> (long) g.getLeft()*g.getRight())
                .reduce(0L, Long::sum);

    }

    private boolean hasAdjacentSymbols(int row, int col) {

        // Incluir adyacencia diagonal también
        int left = col - 1;
        int right = col + 1;
        int top = row - 1;
        int bottom = row + 1;

        // Left
        if(left > 0) {
            if(isSymbol(row, left)) {
                return true;
            }
            if(top > 0) {
                if(isSymbol(top, left)) {
                    return true;
                }
            }
            if(bottom < rows) {
                if(isSymbol(bottom, left)) {
                    return true;
                }
            }
        }

        // Middle
        if(top > 0) {
            if(isSymbol(top, col)) {
                return true;
            }
        }

        if(bottom < rows) {
            if(isSymbol(bottom, col)) {
                return true;
            }
        }

        // Right
        if(right < cols) {
            if(isSymbol(row, right)) {
                return true;
            }
            if(top > 0) {
                if(isSymbol(top, right)) {
                    return true;
                }
            }
            if(bottom < rows) {
                if(isSymbol(bottom, right)) {
                    return true;
                }
            }
        }

        return false;
    }

    private Optional<Vector2> getGearIfPresent(int row, int col, Optional<Vector2> gear) {

        if(gear.isPresent()) {
            return gear;
        }

        // Incluir adyacencia diagonal también
        int left = col - 1;
        int right = col + 1;
        int top = row - 1;
        int bottom = row + 1;

        // Left
        if(left > 0) {
            if(isGear(row, left)) {
                return Optional.of(new Vector2(left, row));
            }
            if(top > 0) {
                if(isGear(top, left)) {
                    return Optional.of(new Vector2(left, top));
                }
            }
            if(bottom < rows) {
                if(isGear(bottom, left)) {
                    return Optional.of(new Vector2(left, bottom));
                }
            }
        }

        // Middle
        if(top > 0) {
            if(isGear(top, col)) {
                return Optional.of(new Vector2(col, top));
            }
        }

        if(bottom < rows) {
            if(isGear(bottom, col)) {
                return Optional.of(new Vector2(col, bottom));
            }
        }

        // Right
        if(right < cols) {
            if(isGear(row, right)) {
                return Optional.of(new Vector2(right, row));
            }
            if(top > 0) {
                if(isGear(top, right)) {
                    return Optional.of(new Vector2(right, top));
                }
            }
            if(bottom < rows) {
                if(isGear(bottom, right)) {
                    return Optional.of(new Vector2(right, bottom));
                }
            }
        }

        return Optional.empty();
    }

    private boolean isGear(int row, int col) {
        return engine[row][col] == GEAR;
    }

    private boolean isSymbol(int row, int col) {
        return !Character.isDigit(engine[row][col]) && engine[row][col] != EMPTY;
    }

}
