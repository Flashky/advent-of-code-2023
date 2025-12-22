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

    private final Set<Cell> startCells = new HashSet<>();

    // Part 1 simulation is always from start
    private Cell start;

    // For part 2
    private final Cell center;
    private final Cell left;
    private final Cell right;
    private final Cell top;
    private final Cell bottom;
    private final Cell topLeft;
    private final Cell topRight;
    private final Cell bottomLeft;
    private final Cell bottomRight;

    private MapStats mapStats;
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

        // Mid
        int mid = rows / 2;

        // Top row
        topLeft = map[0][0];
        top = map[0][mid];
        topRight = map[0][cols-1];

        // Middle row
        left = map[mid][0];
        center = map[mid][mid];
        right = map[mid][cols - 1];

        // Bottom row
        bottomLeft = map[rows-1][0];
        bottom = map[rows-1][mid];
        bottomRight = map[rows-1][cols-1];


        // For part 2

        this.debug = debug;
    }

    public long solveA(int totalSteps) {
        SimulationResult result = bfs(totalSteps, start);
        return totalSteps % 2 == 0 ? result.evenCount() : result.oddCount();
    }

    public long solveB(int totalSteps) {
        mapStats = new MapStats(rows, totalSteps);

        // ODDS AND EVEN SIMULATIONS

        // Center simulation
        SimulationResult result = bfs(mapStats.getCenterSteps(), center);
        long oddTotal =  result.oddCount() * mapStats.getOddCount(); // tiene pinta de estar ok
        long evenTotal = result.evenCount() * mapStats.getEvenCount(); // tiene pinta de estar ok
        // En 7x7:
        // oddTotal = 9 mapas impares con 20 casillas = 180
        // evenTotal = 16 mapas pares con 21 casillas = 336
        reset();

        // CARDINAL SIMULATIONS

        // Cardinal left simulation
        result = bfs(mapStats.getVertexSteps(), right);
        long cardinalLeft = result.evenCount();
        // En 7x7: 16
        reset();

        // Cardinal right simulation
        result = bfs(mapStats.getVertexSteps(), left);
        long cardinalRight = result.evenCount();
        // En 7x7: 16
        reset();

        // Cardinal top simulation
        result = bfs(mapStats.getVertexSteps(), bottom);
        long cardinalTop = result.evenCount();
        // En 7x7: 16
        reset();

        // Cardinal bottom simulation
        result = bfs(mapStats.getVertexSteps(), top);
        // En 7x7: 16
        long cardinalBottom = result.evenCount();
        reset();

        // TRIANGLES SIMULATION

        // Top-left diagonal triangle simulation
        result = bfs(mapStats.getTriangleSteps(), bottomRight);
        long topLeftTriangle = result.evenCount() * mapStats.getTriangleCountPerSide();
        reset();

        // Top-right diagonal triangle simulation
        result = bfs(mapStats.getTriangleSteps(), bottomLeft);
        long topRightTriangle = result.evenCount() * mapStats.getTriangleCountPerSide();
        reset();

        // Bottom-left diagonal triangle simulation
        result = bfs(mapStats.getTriangleSteps(), topRight);
        long bottomLeftTriangle = result.evenCount() * mapStats.getTriangleCountPerSide();
        reset();

        // Bottom-right diagonal triangle simulation
        result = bfs(mapStats.getTriangleSteps(), topLeft);
        long bottomRighTriangle = result.evenCount() * mapStats.getTriangleCountPerSide();
        reset();

        // TRAPEZOIDS SIMULATIONS

        // Top-left diagonal trapezoid simulation
        result = bfs(mapStats.getTrapezoidSteps(), bottomRight);
        // TODO con cuál valor me tengo que quedar?
        long topLeftTrapezoid = result.oddCount() * mapStats.getTrapezoidCountPerSide();
        reset();

        // Top-right diagonal trapezoid simulation
        result = bfs(mapStats.getTrapezoidSteps(), bottomLeft);
        long topRightTrapezoid = result.oddCount() * mapStats.getTrapezoidCountPerSide();
        reset();

        // Bottom-left diagonal trapezoid simulation
        result = bfs(mapStats.getTrapezoidSteps(), topRight);
        long bottomLeftTrapezoid = result.oddCount() * mapStats.getTrapezoidCountPerSide();
        reset();

        // Bottom-right diagonal trapezoid simulation
        result = bfs(mapStats.getTrapezoidSteps(), topLeft);
        long bottomRightTrapezoid = result.oddCount() * mapStats.getTrapezoidCountPerSide();
        reset();

        long finalResult = oddTotal + evenTotal +
                            topLeftTriangle + topRightTriangle + bottomLeftTriangle + bottomRighTriangle
                            + topLeftTrapezoid + topRightTrapezoid + bottomLeftTrapezoid + bottomRightTrapezoid
                             + cardinalLeft + cardinalRight + cardinalBottom + cardinalTop;

        return finalResult;
    }

    private SimulationResult bfs(int totalSteps, Cell start) {
        long oddCells = 0; // TODO cuidado cuando estemos con múltiples celdas, es posible que el número de impares o pares varíe.
        long evenCells = 1; // First cell is even

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

        return new SimulationResult(oddCells, evenCells);
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

    private void reset() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(!map[row][col].isRock()){
                    map[row][col].setVisited(false);
                    map[row][col].setStep(0);
                }
            }
        }
    }
    public void printMap(long steps) {

        System.out.println();
        boolean isEven = steps % 2 == 0;
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                map[row][col].print(isEven);
            }
            System.out.println();
        }
    }



}