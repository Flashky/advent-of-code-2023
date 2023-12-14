package com.adventofcode.flashk.day14;

import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParabolicReflectorDish {

    private static final char ROUNDED_ROCK = 'O';
    private static final char EMPTY = '.';

    private final char[][] map;
    private final int rows;
    private final int cols;

    private final Map<String,Long> snapshots = new HashMap<>();
    private final List<String> orderedSnapshots = new ArrayList<>();

    public ParabolicReflectorDish(char[][] map) {
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
    }

    public long solveA() {
        move(Direction.NORTH);
        return countRocks();
    }

    public long solveB(int cycles) {

        int currentCycle = 0;
        String snapshot;
        boolean cycleFound = false;
        do {

            move(Direction.NORTH);
            move(Direction.WEST);
            move(Direction.SOUTH);
            move(Direction.EAST);
            snapshot = createSnapshot();

            if(snapshots.containsKey(snapshot)) {
                cycleFound = true;
            } else {
                snapshots.put(snapshot, countRocks());
                orderedSnapshots.add(snapshot);
                currentCycle++;
            }

        } while(!cycleFound && (currentCycle < cycles));

        // Cycle detected!
        int nonPatternItems = removeItemsNotInCycle(snapshot);
        int itemsPerPattern = orderedSnapshots.size();
        int patternItems = cycles - nonPatternItems;
        int position = (patternItems-1) % itemsPerPattern;

        String item = orderedSnapshots.get(position);

        return snapshots.get(item);
    }

    private String createSnapshot() {
        StringBuilder snapshotBuilder = new StringBuilder();
        for(int i = 0; i < rows; i++) {
            snapshotBuilder.append(new String(map[i]));
        }
        return snapshotBuilder.toString();
    }

    private int removeItemsNotInCycle(String snapshotCycle) {
        int count = 0;
        while(!snapshotCycle.equals(orderedSnapshots.get(0))) {
            orderedSnapshots.remove(0);
            count++;
        }
        return count;
    }
    private long countRocks() {
        long result = 0;
        int rowCounter = rows;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(map[row][col] == ROUNDED_ROCK) {
                    result += rowCounter;
                }
            }
            rowCounter--;
        }
        return result;
    }
    
    private void move(Direction direction) {
        boolean move;
        do {
            move = tilt(direction);
        } while (move);
    }

    private boolean tilt(Direction direction) {
        boolean move = false;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(canMove(row, col, direction.getMove())){
                    map[row][col] = EMPTY;
                    map[direction.getMove().getY()+row][direction.getMove().getX()+col] = ROUNDED_ROCK;
                    move = true;
                }
            }
        }
        return move;
    }

    private boolean canMove(int row, int col, Vector2 direction) {
        if(map[row][col] != ROUNDED_ROCK) {
            return false;
        }

        int expectedRow = direction.getY() + row;
        int expectedCol = direction.getX() + col;

        if(expectedRow < 0 || expectedRow >= rows) {
            return false;
        }

        if(expectedCol < 0 || expectedCol >= cols) {
            return false;
        }

        return map[expectedRow][expectedCol] == EMPTY;

    }
    
}
