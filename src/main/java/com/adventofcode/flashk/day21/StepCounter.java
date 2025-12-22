package com.adventofcode.flashk.day21;

import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

import static java.lang.IO.println;

public class StepCounter {

    private final Cell[][] map;

    private final int rows;
    private final int cols;

    // Part 1 simulation is always from start
    private Cell start;

    // For part 2
    private final Set<Cell> cardinalStartPoints = new HashSet<>();
    private final Set<Cell> diagonalStartPoints = new HashSet<>();

    // Others
    private final boolean debug;

    public StepCounter(char[][] inputs, boolean debug) {
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

        // For part 2

        int mid = rows / 2;

        // Cardinal start points
        cardinalStartPoints.add(map[0][mid]);           // top
        cardinalStartPoints.add(map[mid][0]);           // left
        cardinalStartPoints.add(map[mid][cols - 1]);    // right
        cardinalStartPoints.add(map[rows-1][mid]);      // bottom

        // Diagonal start points
        diagonalStartPoints.add(map[0][0]);             // top-left
        diagonalStartPoints.add(map[0][cols-1]);        // top-right
        diagonalStartPoints.add(map[rows-1][0]);        // bottom-left
        diagonalStartPoints.add(map[rows-1][cols-1]);   // bottom-right

        this.debug = debug;
    }

    public long solveA(int totalSteps) {
        SimulationResult result = bfs(totalSteps, start);
        return totalSteps % 2 == 0 ? result.evenCount() : result.oddCount();
    }

    public long solveB(int totalSteps) {

        MapStats mapStats = new MapStats(rows, totalSteps);

        // Odd and even maps simulation
        SimulationResult result = bfs(mapStats.getCenterSteps(), start);
        long oddStepsCount =  result.oddCount() * mapStats.getOddCount();
        long evenStepsCount = result.evenCount() * mapStats.getEvenCount();

        // Cardinal maps simulation
        long cardinalStepsCount = calculateMapSteps(cardinalStartPoints,
                                                    mapStats.getCardinalSteps(), mapStats.getCardinalCountPerSide());

        // Diagonal maps simulations
        long triangleStepsCount = calculateMapSteps(diagonalStartPoints,
                                                    mapStats.getTriangleSteps(), mapStats.getTriangleCountPerSide());
        long trapezoidStepsCount = calculateMapSteps(diagonalStartPoints,
                                                    mapStats.getTrapezoidSteps(), mapStats.getTrapezoidCountPerSide());

        return oddStepsCount + evenStepsCount + triangleStepsCount + trapezoidStepsCount + cardinalStepsCount;

    }

    private long calculateMapSteps(Set<Cell> startPoints, int stepsCount, long mapCount) {
        long result = 0;
        boolean isEven = stepsCount % 2 == 0;
        for(Cell startCell : startPoints) {
            SimulationResult simulationResult = bfs(stepsCount, startCell);

            if(isEven) {
                result += simulationResult.evenCount() * mapCount;
            } else {
                result += simulationResult.oddCount() * mapCount;
            }
        }

        return result;
    }

    private SimulationResult bfs(int totalSteps, Cell start) {
        long oddCells = 0;
        long evenCells = 1; // First cell is always even

        Deque<Cell> queue = new ArrayDeque<>();
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

        if(debug) {
            printMap(totalSteps);
        }

        reset();
        return new SimulationResult(oddCells, evenCells);
    }

    private Set<Cell> getAdjacentTiles(Cell currentCell, int maxSteps) {
        Set<Vector2> directions = Set.of(Vector2.left(), Vector2.right(), Vector2.down(), Vector2.up());

        Set<Cell> adjacentTiles = new HashSet<>();

        if(currentCell.getStep() == maxSteps) {
            return adjacentTiles;
        }

        Vector2 position = new Vector2(currentCell.getCol(), currentCell.getRow());

        for(Vector2 dir : directions) {
            Vector2 newPos = Vector2.transform(position, dir);
            if(isValid(newPos)) {
                adjacentTiles.add(map[newPos.getY()][newPos.getX()]);
            }
        }

        return adjacentTiles;
    }

    /// A position is valid if is not out of bounds and does not contain a rock.
    /// @param position the position to check
    /// @return true if the cells is in bounds and not a rock. False otherwise.
    private boolean isValid(Vector2 position) {
        return isNotOutOfBounds(position) && !map[position.getY()][position.getX()].isRock();
    }

    private boolean isNotOutOfBounds(Vector2 position) {
        return (position.getY() >= 0 && position.getY() < rows) && (position.getX() >= 0 && position.getX() < cols);
    }

    private void reset() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                map[row][col].reset();
            }
        }
    }
    public void printMap(long steps) {

        println();
        boolean isEven = steps % 2 == 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                map[row][col].print(isEven);
            }
            println();
        }
    }



}