package com.adventofcode.flashk.day10;

import com.adventofcode.flashk.common.GridUtil;
import com.adventofcode.flashk.common.Vector2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class PipeMaze {

    private static final char GROUND = '.';
    private static final char START = 'S';

    private char[][] map;
    private int rows;
    private int cols;
    private Vector2 startPos;
    private Pipe startPipe;
    private Set<Vector2> startingDirections;

    public PipeMaze(char[][] inputs) {
        map = inputs;
        rows = inputs.length;
        cols = inputs[0].length;

        // Search 'S' and select starting direction
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(map[row][col] == START) {
                    startPos = new Vector2(col, row);
                    startingDirections = findOutValidMovements(row, col);
                }
            }
        }

        int a = 3;
    }

    private Set<Vector2> findOutValidMovements(int row, int col) {

        // Valid pipe directions
        Map<Vector2, Set<Character>> validPipesPerDirection = new HashMap<>();
        validPipesPerDirection.put(Vector2.left(),Set.of('-', 'L', 'F'));
        validPipesPerDirection.put(Vector2.right(),Set.of('-', '7', 'J'));
        validPipesPerDirection.put(Vector2.up(), Set.of('|', 'J', 'L'));
        validPipesPerDirection.put(Vector2.down(), Set.of('|', '7', 'F'));

        Set<Vector2> outputDirections = new HashSet<>();
        for(Vector2 position : GridUtil.getAdjacents(map, row, col)) {

            char value = map[position.getY()][position.getX()];

            // Guess direction
            if(value != GROUND) {
                Vector2 direction = Vector2.substract(position, startPos);
                Set<Character> validPipes = validPipesPerDirection.get(direction);
                if(validPipes.contains(value)) {
                    outputDirections.add(direction);
                }
            }
        }

        return outputDirections;
    }
    public int solveA() {
        int steps = 0;

        Vector2 currentPos = startPos;
        // Just pick any of the two directions.
        Vector2 direction = startingDirections.stream().findFirst().get();

        char tileValue;

        do {
            // Move
            currentPos.transform(direction);
            steps++;
            tileValue = getCharFromPos(currentPos);
            if(tileValue != START) {
                Pipe pipe = Pipe.of(tileValue);
                direction = pipe.direction(direction);
            }

        } while(tileValue != START);

        return steps / 2;
    }

    private char getCharFromPos(Vector2 position) {
        return map[position.getY()][position.getX()];
    }
}
