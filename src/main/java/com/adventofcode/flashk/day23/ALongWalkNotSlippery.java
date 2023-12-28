package com.adventofcode.flashk.day23;

import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

public class ALongWalkNotSlippery {


    private static final char FOREST = '#';
    private static final char PATH = '.';

    private char[][] originalMap;
    private final Tile[][] map;

    private final Tile start;
    private final Tile end;

    private final int rows;
    private final int cols;

    public ALongWalkNotSlippery(char[][] inputs) {

        rows = inputs.length;
        cols = inputs[0].length;

        map = new Tile[rows][];
        originalMap = new char[rows][];

        for(int row = 0; row < rows; row++) {
            map[row] = new Tile[cols];
            originalMap[row] = new char[cols];
            for(int col = 0; col < cols; col++) {
                if(inputs[row][col] == FOREST) {
                    map[row][col] = new Tile(row, col, inputs[row][col]);
                    originalMap[row][col] = inputs[row][col];
                } else {
                    map[row][col] = new Tile(row, col, PATH);
                    originalMap[row][col] = PATH;
                }
            }
        }

        //originalMap = inputs;
        start = map[0][1];
        end = map[rows-1][cols-2];

    }

    public long solveA() {
        return dfs(start.getRow(), start.getCol(), 0);
        //return dfsIterative();
        //return dijkstraReversed();
    }

    /*
    private long dijkstraReversed() {

        start.setTotalSteps(0);

        PriorityQueue<Tile> queue = new PriorityQueue<>();
        queue.add(start);

        while(!queue.isEmpty()) {
            Tile maxTile = queue.poll();
            maxTile.setVisited(true);

            Set<Tile> adjacentTiles = getAdjacentTiles(maxTile.getRow(), maxTile.getCol());

            for(Tile adjacentTile : adjacentTiles) {
                if(!adjacentTile.isVisited()) {
                    int estimatedSteps = maxTile.getTotalSteps() - 1;

                    if(estimatedSteps > adjacentTile.getTotalSteps()) {
                        adjacentTile.setTotalSteps(estimatedSteps);
                        queue.add(adjacentTile);
                    }
                }
            }
        }

        return end.getTotalSteps();
    }*/


    private long dfsIterative() {
        long bestResult = Long.MIN_VALUE;

        Stack<Tile> tiles = new Stack<>();
        tiles.push(start);

        while(!tiles.isEmpty()) {
            Tile currentTile = tiles.pop();

            if(currentTile.getRow() == end.getRow() && currentTile.getCol() == end.getCol()) {
                bestResult = Math.max(bestResult, currentTile.getSteps());
            } else if(!currentTile.isVisited()) {
                currentTile.setVisited(true);
                Set<Tile> adjacentTiles = getAdjacentTiles(currentTile.getRow(), currentTile.getCol());
                for(Tile adjacentTile : adjacentTiles) {
                    adjacentTile.setSteps(currentTile.getSteps()+1);
                    tiles.push(adjacentTile);
                }
            }

            // Backtrack
//            currentTile.setVisited(false);

        }

        return bestResult;
    }

    private long dfs(int row, int col, int steps) {

        if(row == end.getRow() && col == end.getCol()) {
            // Reached destination
            return steps;
        }

        long bestResult = Long.MIN_VALUE;

        // Mark as visited
        Tile currentTile = map[row][col];
        currentTile.setVisited(true);
        originalMap[row][col] = 'O';
        Set<Tile> adjacentTiles = getAdjacentTiles(currentTile.getRow(), currentTile.getCol());
        for(Tile adjacentTile : adjacentTiles) {
            bestResult = Math.max(bestResult, dfs(adjacentTile.getRow(), adjacentTile.getCol(), steps+1));
        }

        // Backtrack
        currentTile.setVisited(false);

        return bestResult;
    }


    private Set<Tile> getAdjacentTiles(int row, int col) {

        Set<Tile> adjacentTiles = new HashSet<>();

        Vector2 currentPos = new Vector2(col, row);

        Tile currentTile = getTile(currentPos);

        // When in a tile:
        // - If the tile is a path, calculate all possible adyacent tiles.
        // - If the tile is a slope, there is only one possible direction.

        if(currentTile.getValue() != PATH) {
            throw new IllegalStateException("Unexpected value: " + currentTile.getValue());
        }

        getTile(currentPos, Vector2.right()).ifPresent(adjacentTiles::add);
        getTile(currentPos, Vector2.left()).ifPresent(adjacentTiles::add);
        getTile(currentPos, Vector2.up()).ifPresent(adjacentTiles::add);
        getTile(currentPos, Vector2.down()).ifPresent(adjacentTiles::add);

        return adjacentTiles;
    }

    /**
     * Calculates a tile next to the current position and direction.
     * <p>
     *     If the next position
     * </p>
     * @param currentPos
     * @param direction
     * @return an {@link Optional} containing the adjacent tile, if there is any, at that direction.
     * Will be {@link Optional#empty} if index is out of bounds or if the adjacent tile is a forest or has been already visited.
     */
    private Optional<Tile> getTile(Vector2 currentPos, Vector2 direction) {
        Vector2 nextPos = Vector2.transform(currentPos, direction);

        if(isOutOfBounds(nextPos)) {
            return Optional.empty();
        }

        Tile tile = getTile(nextPos);
        if(tile.isVisited()) {
            return Optional.empty();
        }

        return switch(tile.getValue()) {
            case FOREST -> Optional.empty();
            case PATH -> Optional.of(tile);
            default -> throw new IllegalStateException("Unexpected value: " + tile.getValue());
        };

    }

    private boolean isOutOfBounds(Vector2 position) {
        return (position.getY() < 0 || position.getY() >= rows) || (position.getX() < 0 || position.getX() >= cols);
    }

    private Tile getTile(Vector2 position) {
        return map[position.getY()][position.getX()];
    }
}
