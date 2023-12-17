package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;

import java.util.HashSet;

import java.util.PriorityQueue;
import java.util.Set;

public class ClumsyCrucible {

    private Tile destination;
    private Tile[][] map;
    private final int rows;
    private final int cols;

    public ClumsyCrucible(int[][] inputs) {
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

        // TODO idea:
        // Una PriorityQueue que almacene: (Tile tile, Vector2 travelDirection, Integer travelLength)

        PriorityQueue<Tile> queue = new PriorityQueue<>();

        Vector2 travelDirection = new Vector2(0,0);
        Tile startingTile = map[0][0];
        //startingTile.visit(travelDirection);
        startingTile.setTotalHeatloss(0);
        startingTile.setTravelLength(0);

        queue.add(startingTile);

        while(!queue.isEmpty()) {

            // OK
            Tile minTile = queue.poll();
            Set<Tile> adjacentTiles = getAdjacentTiles(minTile);

            for(Tile adjacentTile : adjacentTiles) {

                // Get tuple of travelDirection and length
                travelDirection = Vector2.substract(adjacentTile.getPosition(), minTile.getPosition());
                int travelLength;
                if(minTile.getTravelDirection().equals(travelDirection)) {
                    travelLength =  0;// minTile.getTravelLength(travelDirection) + 1;
                } else {
                    travelLength = 1;
                }

                // TODO y la longitud?
                /*
                if(!adjacentTile.isVisited(travelDirection, travelLength)){

                    adjacentTile.setTravelDirection(travelDirection);

                    if(minTile.getTravelDirection().equals(travelDirection)) {
                        adjacentTile.setTravelLength(minTile.getTravelLength() + 1);
                    } else {
                        adjacentTile.setTravelLength(1);
                    }



                    // Cost of moving to the adjacent tile
                    int heatloss = adjacentTile.getHeatloss();

                    // Cost of moving to the adjacent tile + total cost to reach to this tile
                    int estimatedHeatloss = heatloss + minTile.getTotalHeatloss();

                    if(adjacentTile.getTotalHeatloss() > estimatedHeatloss && adjacentTile.getTravelLength() < 4) {
                        adjacentTile.setTotalHeatloss(estimatedHeatloss);
                        queue.add(adjacentTile);
                    }

                }*/
            }
        }
        return destination.getTotalHeatloss();
    }

    private Set<Tile> getAdjacentTiles(Tile tile) {
        Set<Tile> adjacentTiles = new HashSet<>();

        int col = tile.getCol();
        int row = tile.getRow();

        int right = col+1;
        int left = col-1;
        int up = row-1;
        int down = row+1;

        if(!isOutOfBounds(row, right)) {
            adjacentTiles.add(map[row][right]);
        }

        if(!isOutOfBounds(row, left)) {
            adjacentTiles.add(map[row][left]);
        }

        if(!isOutOfBounds(up, row)) {
            adjacentTiles.add(map[up][col]);
        }

        if(!isOutOfBounds(down, row)) {
            adjacentTiles.add(map[down][col]);
        }

        return adjacentTiles;

    }

    private boolean isOutOfBounds(int row, int col) {
        return (row >= cols || row < 0) || (col >= rows || col < 0);
    }

    /*
    public long dfs(Tile currentTile, Vector2 currentDirection, int straightCount) {

        currentTile.setVisited(true);
        if(currentTile.getRow() == rows-1 && currentTile.getCol() == cols-1) {
            return currentTile.getHeatLoss();
        }

        long currentTileHeatLoss = currentTile.getHeatLoss();
        long bestHeatLoss = Long.MAX_VALUE;
        List<Vector2> nextDirections = currentTile.nextDirections(currentDirection);

        for(Vector2 nextDirection : nextDirections) {

            Optional<Tile> nextTile = nextTile(nextDirection, currentTile);

            if(nextTile.isPresent()) {
                if(nextDirection.equals(currentDirection) && straightCount < 3){
                    long heatLossBranch = currentTileHeatLoss + dfs(nextTile.get(), nextDirection, straightCount+1);
                    bestHeatLoss = Math.min(heatLossBranch, bestHeatLoss);
                    //nextTile.get().setVisited(false);
                } else {
                    long heatLossBranch = currentTileHeatLoss + dfs(nextTile.get(), nextDirection, 1);
                    bestHeatLoss = Math.min(heatLossBranch, bestHeatLoss);
                    //nextTile.get().setVisited(false);
                }
            }
        }

        //currentTile.setVisited(false);
        return bestHeatLoss;
    }


    private Optional<Tile> nextTile(Vector2 nextDirection, Tile currentTile) {

        Vector2 currentTilePos = new Vector2(currentTile.getCol(), currentTile.getRow());
        Vector2 nextPosition = Vector2.transform(nextDirection, currentTilePos);

        // Exclude out of bounds tiles
        if(nextPosition.getX() >= 0 &&
                nextPosition.getX() < cols &&
                nextPosition.getY() >= 0 &&
                nextPosition.getY() < rows) {

            Tile nextTile = map[nextPosition.getY()][nextPosition.getX()];

            // Exclude tiles that have been already
            if(!nextTile.isVisited()) {
                return Optional.of(nextTile);
            }
        }

        return Optional.empty();
    }*/

}
