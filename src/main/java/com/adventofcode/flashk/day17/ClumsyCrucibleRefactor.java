package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class ClumsyCrucibleRefactor {

    public static final Vector2 RIGHT = Vector2.right();
    public static final Vector2 LEFT = Vector2.left();
    public static final Vector2 UP = Vector2.down();
    public static final Vector2 DOWN = Vector2.up();

    private final Tile destination;
    private final Tile[][] map;
    private final int rows;
    private final int cols;

    public ClumsyCrucibleRefactor(int[][] inputs) {
        rows = inputs.length;
        cols = inputs[0].length;
        map = new Tile[this.rows][];
        for(int row = 0; row < rows; row++) {
            this.map[row] = new Tile[cols];
            for(int col = 0; col < cols; col++) {
                this.map[row][col] = new Tile(inputs[row][col], row, col);
            }
        }
        destination = map[rows-1][cols-1];
    }

    public long solveA() {
        PriorityQueue<TileStatus> queue = new PriorityQueue<>();

        Tile root = map[0][0];
        root.setTotalHeatloss(0);

        queue.add(new TileStatus(root, new Vector2(0,0), 0));

        // Start algorithm
        while(!queue.isEmpty()) {

            // Get a vertex+direction+travel distance combination
            TileStatus minTileStatus = queue.poll();

            // Mark as visited
            Tile minTile = minTileStatus.getTile();
            Vector2 direction = minTileStatus.getDirection();
            int steps = minTileStatus.getLength();
            minTile.visit(ImmutablePair.of(direction, steps));

            // Obtain candidates for Tile
            List<TileStatus> adjacentTiles = getAdjacentTiles(minTileStatus);

            for(TileStatus nextTileStatus : adjacentTiles) {
                Tile adjacentTile = nextTileStatus.getTile();
                direction = nextTileStatus.getDirection();
                steps = nextTileStatus.getLength();

                if(!adjacentTile.isVisited(ImmutablePair.of(direction, steps))) {

                    // Cost of moving to the adjacent node
                    int heatloss = adjacentTile.getHeatloss();

                    // Cost of moving to the adjacent node + total cost to reach to this node
                    int estimatedHeatloss = minTile.getTotalHeatloss() + heatloss;

                    if(adjacentTile.getTotalHeatloss() > estimatedHeatloss) {
                        adjacentTile.setTotalHeatloss(estimatedHeatloss);
                        queue.add(nextTileStatus);
                    }

                }
            }
        }

        return destination.getTotalHeatloss();
    }

    private List<TileStatus> getAdjacentTiles(TileStatus currentTileStatus) {

        List<TileStatus> tileStatuses = new LinkedList<>();

        Tile currentTile = currentTileStatus.getTile();
        Vector2 currentTilePos = new Vector2(currentTile.getCol(), currentTile.getRow());
        Vector2 currentDirection = currentTileStatus.getDirection();
        int currentDirectionSteps = currentTileStatus.getLength();

        // Filter reversed direction
        Vector2 reversedDirection = new Vector2(currentDirection);
        reversedDirection.multiply(-1);

        // Right
        Vector2 nextDirection = RIGHT;
        Vector2 nextPosition = Vector2.transform(currentTilePos, nextDirection);
        int nextDirectionSteps = nextSteps(nextDirection, currentDirection, currentDirectionSteps);
        
        if(isNotOutOfBounds(nextPosition) && !nextDirection.equals(reversedDirection) && nextDirectionSteps < 4) {
            tileStatuses.add(createTileStatus(nextPosition, nextDirection, nextDirectionSteps));
        }
        
        // Left
        nextDirection = LEFT;
        nextPosition = Vector2.transform(currentTilePos, nextDirection);
        nextDirectionSteps = nextSteps(nextDirection, currentDirection, currentDirectionSteps);

        if(isNotOutOfBounds(nextPosition) && !nextDirection.equals(reversedDirection) && nextDirectionSteps < 4) {
            tileStatuses.add(createTileStatus(nextPosition, nextDirection, nextDirectionSteps));
        }

        // Down
        nextDirection = DOWN;
        nextPosition = Vector2.transform(currentTilePos, nextDirection);
        nextDirectionSteps = nextSteps(nextDirection, currentDirection, currentDirectionSteps);

        if(isNotOutOfBounds(nextPosition) && !nextDirection.equals(reversedDirection) && nextDirectionSteps < 4) {
            tileStatuses.add(createTileStatus(nextPosition, nextDirection, nextDirectionSteps));
        }

        // Up
        nextDirection = UP;
        nextPosition = Vector2.transform(currentTilePos, nextDirection);
        nextDirectionSteps = nextSteps(nextDirection, currentDirection, currentDirectionSteps);

        if(isNotOutOfBounds(nextPosition) && !nextDirection.equals(reversedDirection) && nextDirectionSteps < 4) {
            tileStatuses.add(createTileStatus(nextPosition, nextDirection, nextDirectionSteps));
        }

        return tileStatuses;
    }


    private TileStatus createTileStatus(Vector2 nextPosition, Vector2 nextDirection, int nextDirectionSteps) {
        Tile nextTile = map[nextPosition.getY()][nextPosition.getX()];
        return new TileStatus(nextTile, nextDirection, nextDirectionSteps);
    }

    private int nextSteps(Vector2 nextDirection, Vector2 currentDirection, int currentSteps) {
        if(nextDirection.equals(currentDirection)) {
            return currentSteps + 1;
        }
        return 1;
    }

    private boolean isNotOutOfBounds(Vector2 position) {
        return (position.getY() >= 0 && position.getY() < rows) && (position.getX() >= 0 && position.getX() < cols);
    }

}
