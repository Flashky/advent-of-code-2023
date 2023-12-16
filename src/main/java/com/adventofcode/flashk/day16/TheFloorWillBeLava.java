package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class TheFloorWillBeLava {

    private Tile[][] map;
    //private char[][] solutionMap;
    private int rows;
    private int cols;

    public TheFloorWillBeLava(char[][] map) {
        this.map = new Tile[map.length][];
        this.rows = map.length;
        this.cols = map[0].length;
        for(int row = 0; row < rows; row++) {
            this.map[row] = new Tile[cols];
            for(int col = 0; col < cols; col++) {
                this.map[row][col] = buildTile(map[row][col], row, col);
            }
        }
    }

    public long solveA(){
        long result = 0;

        // Start conditions
        Tile startingTile = map[0][0];
        Vector2 startingDirection = Vector2.right();

        Queue<Tile> tilesQueue = new LinkedList<>();
        Queue<Vector2> directionsQueue = new LinkedList<>();
        startingTile.visit(startingDirection);
        tilesQueue.add(startingTile);
        directionsQueue.add(startingDirection);

        // TODO cualquier tile solo se marca como completamente visitada si es visitada desde las cuatro direcciones.
        while(!tilesQueue.isEmpty()) {
            Tile currentTile = tilesQueue.poll();
            Vector2 currentDirection = directionsQueue.poll();
            List<Vector2> nextDirections = currentTile.nextDirections(currentDirection);

            for(Vector2 nextDirection : nextDirections) {
                Optional<Tile> nextTile = nextTile(nextDirection,currentTile);
                if(nextTile.isPresent()) {
                    nextTile.get().visit(nextDirection);
                    tilesQueue.add(nextTile.get());
                    directionsQueue.add(nextDirection);
                }

            }


        }

        return countVisitedTiles();
    }


    private long bfs(Vector2 startPosition, Vector2 endPosition) {

    }

    public long solveB() {
        // Possible starts

        // Left edge
        Vector2 startingDirection = Vector2.right();
        for(int row = 0; row < rows; row++) {
            Vector2 startingPosition = new Vector2(0,row);
        }
        // Right edge
        startingDirection = Vector2.left();


        // Top edge
        Vector2 startingDirection = Vector2.down();

        // Bottom edge
        startingDirection = Vector2.up();


    }
    private Optional<Tile> nextTile(Vector2 nextDirection, Tile currentTile) {

        Vector2 currentTilePos = new Vector2(currentTile.getCol(), currentTile.getRow());
        Vector2 nextPosition = Vector2.transform(nextDirection, currentTilePos);

        if(nextPosition.getX() >= 0 &&
                nextPosition.getX() < cols &&
                nextPosition.getY() >= 0 &&
                nextPosition.getY() < rows) {

            Tile nextTile = map[nextPosition.getY()][nextPosition.getX()];

            // Next tile must NOT have been visited from that direction
            if(!nextTile.isVisited(nextDirection)) {
                return Optional.of(nextTile);
            }
        }

        return Optional.empty();
    }

    private long countVisitedTiles() {
        long count = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(map[row][col].isVisitedOnce()) {
                    count++;
                }
            }
        }

        return count;
    }

    private Tile buildTile(char value, int row, int col) {

        return switch (value) {
            case '.': yield new EmptyTile(value, row, col);
            case '\\': yield new MirrorTileLeft(value, row, col);
            case '/': yield new MirrorTileRight(value, row, col);
            case '|': yield new SplitterTileVertical(value, row, col);
            case '-': yield new SplitterTileHorizontal(value, row, col);
            default: throw new IllegalArgumentException("Unexpected value: "+value);
        };
    }
}
