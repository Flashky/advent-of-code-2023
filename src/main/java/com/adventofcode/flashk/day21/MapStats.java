package com.adventofcode.flashk.day21;

import module java.base;
import lombok.Getter;

/// Helper class to make calculations for the simulations of {@link StepCounter}.
///
/// For a given grid size and number steps, it pre-calculates and stores the following information:
/// - The total number of steps to simulate on each kind of map.
/// - The total number of maps of each type.
@Getter
public class MapStats {

    // Basic puzzle map information
    private final int gridSize;
    private final int steps;
    private final int n;

    // Steps to simulate on each type of map
    private final int centerSteps;    // Center map, which is the same as any other odd/even complete map
    private final int vertexSteps;    // Cardinal maps
    private final int triangleSteps;  // Diagonal triangle maps
    private final int trapezoidSteps; // Diagonal trapezoid maps

    // Count of different type maps
    private final long oddCount;
    private final long evenCount;
    private final long vertexCountPerSide = 1;  // There are 4 different vertices, and there is only 1 per side.
    private final long triangleCountPerSide;    // There are 4 different sides, with n maps on each side.
    private final long trapezoidCountPerSide;   // There are 4 different sides, with n-1 maps on each side.


    public MapStats(int gridSize, int steps) {

        // Basic puzzle information
        this.gridSize = gridSize;
        this.steps = steps;
        this.n = steps / gridSize;

        // Calculate steps
        this.centerSteps = gridSize;
        this.vertexSteps = gridSize - 1;
        this.triangleSteps = calculateTriangleSteps(steps, gridSize);
        this.trapezoidSteps = gridSize + triangleSteps;

        // Calculate different types of maps
        this.oddCount = (long) Math.pow(n-1, 2);
        this.evenCount = (long) Math.pow(n, 2);
        this.triangleCountPerSide = n;
        this.trapezoidCountPerSide = n - 1;
    }

    private int calculateTriangleSteps(int steps, int gridSize) {
        // Formula: (grid size + 1) div 2
        int stepsToVertex = steps - gridSize + 1; // For 17 steps on a 7x7 matrix it would be 17-6 = 11
        int amountToReduce = (gridSize + 1) / 2; // For 7x7 matrix it would be 4
        int traveledAmount = stepsToVertex + amountToReduce; // Now it would be 11 + 4 = 15
        return steps - traveledAmount; // 17 - 15 = 2 steps
    }

}
