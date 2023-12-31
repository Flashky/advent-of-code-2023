package com.adventofcode.flashk.day21;

import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

public class StepCounter {

    private final Cell[][] map;

    private final int rows;
    private final int cols;

    private Cell start;

    public StepCounter(char[][] inputs) {

        rows = inputs.length;
        cols = inputs[0].length;

        // Initialize map
        map = new Cell[rows][];
        for(int row = 0; row < rows; row++) {
            map[row] = new Cell[cols];
            for(int col = 0; col < cols; col++) {
                map[row][col] = new Cell(row, col, inputs[row][col]);
                if(inputs[row][col] == 'S') {
                    start = map[row][col];
                }

            }
        }
    }

    public long solveA(int totalSteps) {

        Deque<Cell> queue = new ArrayDeque<>();

        long oddCells = 0;
        long evenCells = 1; // First cell is even

        start.setVisited(true);
        queue.add(start);

        while(!queue.isEmpty()) {
            Cell currentCell = queue.poll();

            Set<Cell> adjacentCells = getAdjacentTiles(currentCell, totalSteps);
            for(Cell adjacentCell : adjacentCells) {
                if(!adjacentCell.isVisited()) {
                    adjacentCell.setVisited(true);
                    adjacentCell.setStep(currentCell.getStep()+1);
                    if(adjacentCell.isEven()) {
                        evenCells++;
                    } else {
                        oddCells++;
                    }
                    queue.add(adjacentCell);
                }
            }
        }

        return totalSteps % 2 == 0 ? evenCells : oddCells;
    }



    private Set<Cell> getAdjacentTiles(Cell currentCell, int maxSteps) {

        Set<Cell> adjacentTiles = new HashSet<>();

        if(currentCell.getStep() == maxSteps) {
            return adjacentTiles;
        }

        Vector2 position = new Vector2(currentCell.getCol(), currentCell.getRow());

        // Possible positions
        Vector2 left = Vector2.transform(position, Vector2.left());
        Vector2 right = Vector2.transform(position, Vector2.right());
        Vector2 up = Vector2.transform(position, Vector2.down());
        Vector2 down = Vector2.transform(position, Vector2.up());

        // Add valid movements to the adjacent set
        if(isValid(left)) {
            adjacentTiles.add(map[left.getY()][left.getX()]);
        }

        if(isValid(right)) {
            adjacentTiles.add(map[right.getY()][right.getX()]);
        }

        if(isValid(up)) {
            adjacentTiles.add(map[up.getY()][up.getX()]);
        }

        if(isValid(down)) {
            adjacentTiles.add(map[down.getY()][down.getX()]);
        }

        return adjacentTiles;
    }

    /**
     * A position is valid if is not out of bounds and does not contain a rock.
     * @param position the position to check
     * @return true if the cells is in bounds and not a rock. False otherwise.
     */
    private boolean isValid(Vector2 position) {
        return isNotOutOfBounds(position) && !map[position.getY()][position.getX()].isRock();
    }

    private boolean isNotOutOfBounds(Vector2 position) {
        return (position.getY() >= 0 && position.getY() < rows) && (position.getX() >= 0 && position.getX() < cols);
    }
}
