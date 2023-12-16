package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

public class TheFloorWillBeLava {

    private final Tile[][] map;
    private final int rows;
    private final int cols;

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
        return bfs(new Vector2(0,0), Vector2.right());
    }


    private long bfs(Vector2 startPosition, Vector2 startingDirection) {

        Queue<Tile> tilesQueue = new LinkedList<>();
        Queue<Vector2> directionsQueue = new LinkedList<>();

        Tile startingTile = map[startPosition.getY()][startPosition.getX()];
        startingTile.visit(startingDirection);

        tilesQueue.add(startingTile);
        directionsQueue.add(startingDirection);

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

    public long solveB() {
        // Possible starts

        long result = Long.MIN_VALUE;

        // Left edge
        Vector2 startingDirection = Vector2.right();

        for(int row = 0; row < rows; row++) {
            Vector2 startingPosition = new Vector2(0,row);
            result = Math.max(result, bfs(startingPosition, startingDirection));
            resetMap();
        }

        // Right edge
        startingDirection = Vector2.left();
        for(int row = 0; row < rows; row++) {
            Vector2 startingPosition = new Vector2(cols-1,row);
            result = Math.max(result, bfs(startingPosition, startingDirection));
            resetMap();
        }

        // Top edge
        startingDirection = Vector2.up();
        for(int col = 0; col < cols; col++) {
            Vector2 startingPosition = new Vector2(col,0);
            result = Math.max(result, bfs(startingPosition, startingDirection));
            resetMap();
        }

        // Bottom edge
        startingDirection = Vector2.down();
        for(int col = 0; col < cols; col++) {
            Vector2 startingPosition = new Vector2(col,rows-1);
            result = Math.max(result, bfs(startingPosition, startingDirection));
            resetMap();
        }

        return result;

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

            // Exclude tiles that have been already visited from that direction
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
            case '.': yield new EmptyTile(row, col);
            case '\\': yield new MirrorLeftTile(row, col);
            case '/': yield new MirrorRightTile(row, col);
            case '|': yield new SplitterVerticalTile(row, col);
            case '-': yield new SplitterHorizontalTile(row, col);
            default: throw new IllegalArgumentException("Unexpected value: "+value);
        };
    }

    private void resetMap() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                map[row][col].reset();
            }
        }
    }
}
