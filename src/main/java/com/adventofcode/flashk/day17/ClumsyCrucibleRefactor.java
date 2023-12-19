package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

public class ClumsyCrucibleRefactor {
    private Tile destination;
    private Tile[][] map;
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

        Vector2 startPosition = new Vector2(0,0);
        int startingTravelDistance = 0;
        Tile startingTile = map[startPosition.getY()][startPosition.getX()];
        startingTile.setTotalHeatloss(0);

        queue.add(new TileStatus(startingTile, new Vector2(1,0), startingTravelDistance));
        //queue.add(new TileStatus(startingTile, new Vector2(0,1), startingTravelDistance));

        // Start algorithm
        while(!queue.isEmpty()) {

            // Get a vertex+direction+travel distance combination
            TileStatus currentTileTriple = queue.poll();
            Tile currentTile = currentTileTriple.getTile();
            Vector2 currentDirection = currentTileTriple.getDirection();
            int currentTravelDistance = currentTileTriple.getLength();
            //Pair<Vector2, Integer> visitedPair = ImmutablePair.of(currentDirection, currentTravelDistance);
            //currentTile.visit(visitedPair);

            // Obtain candidates for Tile
            List<Pair<Vector2,Integer>> nextDirections = currentTile.nextDirections(currentDirection, currentTravelDistance);

            for(Pair<Vector2, Integer> nextDirection : nextDirections) {
                Optional<Tile> nextTile = nextTile(nextDirection, currentTile);
                if(nextTile.isPresent()) {
                    nextTile.get().visit(nextDirection);
                    TileStatus nextTileTriple = new TileStatus(nextTile.get(), nextDirection.getLeft(), nextDirection.getRight());
                    queue.add(nextTileTriple);
                }
            }
        }

        return destination.getTotalHeatloss();
    }

    private Optional<Tile> nextTile(Pair<Vector2, Integer> nextDirection, Tile currentTile){

        Vector2 currentTilePos = new Vector2(currentTile.getCol(), currentTile.getRow());
        Vector2 nextDirectionVector2 = nextDirection.getLeft();
        Vector2 nextPosition = Vector2.transform(nextDirectionVector2, currentTilePos);

        // Exclude out of bounds tiles
        if(nextPosition.getX() >= 0 &&
                nextPosition.getX() < cols &&
                nextPosition.getY() >= 0 &&
                nextPosition.getY() < rows) {


            Tile nextTile = map[nextPosition.getY()][nextPosition.getX()];

            // Cost of moving to the adjacent tile
            int heatloss = nextTile.getHeatloss();

            // Cost of moving to the adjacent tile + total cost to reach to this tile
            int estimatedHeatloss = heatloss + currentTile.getTotalHeatloss();

            // Dijkstra condition Include only if next direction is not visited and it is promising.
            if(!nextTile.isVisited(nextDirection) && nextTile.getTotalHeatloss() > estimatedHeatloss) {
                nextTile.setTotalHeatloss(estimatedHeatloss);
                return Optional.of(nextTile);
            }


        }

        return Optional.empty();
    }




}
