package com.adventofcode.flashk.day23;

import com.adventofcode.flashk.common.Vector2;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ALongWalk {


    private static final char FOREST = '#';
    private static final char PATH = '.';
    private static final char SLOPE_RIGHT = '>';
    private static final char SLOPE_LEFT = '<';
    private static final char SLOPE_DOWN = 'v';
    private static final char SLOPE_UP = '^';

    private char[][] originalMap;
    private final Tile[][] map;

    private final Tile start;
    private final Tile end;

    private final int rows;
    private final int cols;

    public ALongWalk(char[][] inputs) {

        rows = inputs.length;
        cols = inputs[0].length;

        map = new Tile[rows][];
        originalMap = inputs;

        for(int row = 0; row < rows; row++) {
            map[row] = new Tile[cols];
            for(int col = 0; col < cols; col++) {
                map[row][col] = new Tile(row, col, inputs[row][col]);
            }
        }

        start = map[0][1];
        end = map[rows-1][cols-2];

    }

    public long solveA() {
        return dfs(start.getRow(), start.getCol(), 0);
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

        switch(currentTile.getValue()) {
            case PATH:
                getTile(currentPos, Vector2.right()).ifPresent(adjacentTiles::add);
                getTile(currentPos, Vector2.left()).ifPresent(adjacentTiles::add);
                getTile(currentPos, Vector2.up()).ifPresent(adjacentTiles::add);
                getTile(currentPos, Vector2.down()).ifPresent(adjacentTiles::add);
                break;
            case SLOPE_RIGHT:
                getTile(currentPos, Vector2.right()).ifPresent(adjacentTiles::add);
                break;
            case SLOPE_LEFT:
                getTile(currentPos, Vector2.left()).ifPresent(adjacentTiles::add);
                break;
            case SLOPE_DOWN:
                getTile(currentPos, Vector2.up()).ifPresent(adjacentTiles::add);
                break;
            case SLOPE_UP:
                getTile(currentPos, Vector2.down()).ifPresent(adjacentTiles::add);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + currentTile.getValue());
        }


        return adjacentTiles;
    }

    /**
     * Calculates a tile next to the current position and direction.
     * <p>
     *     If the next position
     * </p>
     * @param currentPos current position
     * @param direction the direction to apply
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

        // Suppose:
        // #v#
        // >.>
        // #v#
        //
        // Only directions allowed would be to slope right (Vector2.right) and slope down (Vector2.up).

        return switch(tile.getValue()) {
            case FOREST -> Optional.empty();
            case PATH -> Optional.of(tile);
            case SLOPE_RIGHT -> Vector2.right().equals(direction) ? Optional.of(tile) : Optional.empty();
            case SLOPE_LEFT -> Vector2.left().equals(direction) ? Optional.of(tile) : Optional.empty();
            case SLOPE_DOWN -> Vector2.up().equals(direction) ? Optional.of(tile) : Optional.empty();
            case SLOPE_UP -> Vector2.down().equals(direction) ? Optional.of(tile) : Optional.empty();
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