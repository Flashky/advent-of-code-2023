package com.adventofcode.flashk.day21;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class StepCounter {

    private static final char ROCK = '#';
    private static final char GARDEN_PLOT = '.';
    private static final char REACH_TILE = '0';

    private char[][] map;
    private char[][] solutionsMap;

    private int rows;
    private int cols;
    private int reachableTiles = 0;
    private Vector2 start;

    public StepCounter(char[][] inputs) {

        rows = inputs.length;
        cols = inputs[0].length;
        map = inputs;

        // Find starting position

        solutionsMap = new char[rows][];
        for(int row = 0; row < rows; row++) {

            solutionsMap[row] = new char[cols];

            for(int col = 0; col < cols; col++) {
                if(map[row][col] == 'S') {
                    start = new Vector2(col, row);
                    map[row][col] = GARDEN_PLOT;
                }
                solutionsMap[row][col] = map[row][col];

            }
        }
    }

    public long solveA(int totalSteps) {

        // TODO, este algoritmo vale para datos muy pequeños, pero en este caso, la ramificación es muy amplia.
        Deque<Pair<Vector2,Integer>> queue = new ArrayDeque<>();
        queue.add(ImmutablePair.of(start,0));

        while(!queue.isEmpty() && queue.peek().getRight() < totalSteps) {
            Pair<Vector2,Integer> positionAndSteps = queue.poll();

            Vector2 position = positionAndSteps.getLeft();
            int steps = positionAndSteps.getRight();

            map[position.getY()][position.getX()] = GARDEN_PLOT;

            Set<Pair<Vector2,Integer>> adjacentTiles = getAdjacentTiles(position, steps);
            for(Pair<Vector2,Integer> positionAndStep : adjacentTiles) {
                position = positionAndStep.getLeft();
                map[position.getY()][position.getX()] = REACH_TILE;
                queue.add(positionAndStep);
            }
        }

        return countPositions();
    }

    public long solveADFS(int totalSteps) {

        Set<Vector2> reachablePositions = new HashSet<>();
        /*
        long result = countReachableTiles(start.getY(),start.getX()+1, 1, totalSteps);
        result += countReachableTiles(start.getY(), start.getX()-1, 1, totalSteps);
        result += countReachableTiles(start.getY()-1, start.getX(), 1, totalSteps);
        result += countReachableTiles(start.getY()+1, start.getX(), 1, totalSteps);

        return result;
         */

        countReachableTiles(start.getY(),start.getX()+1, 1, totalSteps, reachablePositions);
        countReachableTiles(start.getY(), start.getX()-1, 1, totalSteps, reachablePositions);
        countReachableTiles(start.getY()-1, start.getX(), 1, totalSteps, reachablePositions);
        countReachableTiles(start.getY()+1, start.getX(), 1, totalSteps, reachablePositions);

        return reachablePositions.size();
    }

    private void countReachableTiles(int row, int col, int steps, int maxSteps, Set<Vector2> reachablePositions) {

        if(!isValid(row, col)) {
            return;
        }

        if(steps == maxSteps) {
            reachablePositions.add(new Vector2(col, row));
            solutionsMap[row][col] = REACH_TILE;
            return;
        }

        // TODO esta condición está mal
        // Es necesario podar ramas que ya hayamos visitado para reducir el árbol de llamadas, pero hay que ver como.
        /*if(solutionsMap[row][col] == REACH_TILE) {
            return; // Already explored
        }*/

        if(maxSteps % 2 == 0) {
            // Se buscan celdas pares
            if(steps % 2 == 0) {
                reachablePositions.add(new Vector2(col, row));
                solutionsMap[row][col] = REACH_TILE;
            }

        } else {
            // Se buscan celdas impares
            if(steps % 2 != 0) {
                reachablePositions.add(new Vector2(col, row));
                solutionsMap[row][col] = REACH_TILE;
            }
        }

        countReachableTiles(row,col+1, steps+1, maxSteps, reachablePositions);
        countReachableTiles(row, col-1, steps+1, maxSteps, reachablePositions);
        countReachableTiles(row-1, col, steps+1, maxSteps, reachablePositions);
        countReachableTiles(row+1, col, steps+1, maxSteps, reachablePositions);

    }

    private long countReachableTiles(int row, int col, int steps, int maxSteps) {

        if(!isValid(row, col)) {
            return 0;
        }

        if(steps == maxSteps) {
            return 1;
        }

        long result = countReachableTiles(row,col+1, steps+1, maxSteps);
        result += countReachableTiles(row, col-1, steps+1, maxSteps);
        result += countReachableTiles(row-1, col, steps+1, maxSteps);
        result += countReachableTiles(row+1, col, steps+1, maxSteps);

        return result;
    }


    private long countPositions() {
        long count = 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(map[row][col] == REACH_TILE) {
                    count++;
                }
            }
        }
        return count;
    }
    private Set<Pair<Vector2,Integer>> getAdjacentTiles(Vector2 position, int stepCounter) {

        Set<Pair<Vector2,Integer>> adjacentTiles = new HashSet<>();

        //Vector2 position = positionAndSteps.getLeft();
        //int stepCounter = positionAndSteps.getRight() + 1;

        // Possible positions
        Vector2 left = Vector2.transform(position, Vector2.left());
        Vector2 right = Vector2.transform(position, Vector2.right());
        Vector2 up = Vector2.transform(position, Vector2.down());
        Vector2 down = Vector2.transform(position, Vector2.up());

        // Add valid movements to the adjacent set
        if(isValid(left)) {
            adjacentTiles.add(ImmutablePair.of(left, stepCounter+1));
        }

        if(isValid(right)) {
            adjacentTiles.add(ImmutablePair.of(right, stepCounter+1));
        }

        if(isValid(up)) {
            adjacentTiles.add(ImmutablePair.of(up, stepCounter+1));
        }

        if(isValid(down)) {
            adjacentTiles.add(ImmutablePair.of(down, stepCounter+1));
        }

        return adjacentTiles;
    }

    private boolean isValid(Vector2 position) {
        return isNotOutOfBounds(position) && map[position.getY()][position.getX()] != ROCK;
    }

    private boolean isValid(int row, int col) {
        // Empty tile that is in limits
        // TODO we don't want to repeat movements
        return isNotOutOfBounds(row, col) && map[row][col] == GARDEN_PLOT;
    }
    private boolean isNotOutOfBounds(int row, int col) {
        return (row >= 0 && row < rows) && (col >= 0 && col < cols);
    }

    private boolean isNotOutOfBounds(Vector2 position) {
        return (position.getY() >= 0 && position.getY() < rows) && (position.getX() >= 0 && position.getX() < cols);
    }
}
