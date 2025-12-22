package com.adventofcode.flashk.day21;

import static org.junit.jupiter.api.Assertions.*;
import static java.lang.IO.println;

import module java.base;
import org.junit.jupiter.api.Test;

class MapStatsTest {

    @Test
    void grid7x7With17StepsTest() {

        // Arrange & Act
        MapStats mapStats = new MapStats(7, 17);

        // Assert basic stats
        assertEquals(7, mapStats.getGridSize());
        assertEquals(17, mapStats.getSteps());
        assertEquals(2, mapStats.getN());

        // Assert steps
        assertEquals(7, mapStats.getCenterSteps());
        assertEquals(6, mapStats.getCardinalSteps());
        assertEquals(2, mapStats.getTriangleSteps());
        assertEquals(9, mapStats.getTrapezoidSteps());

        // Assert count of different maps
        assertEquals(1, mapStats.getOddCount());
        assertEquals(4, mapStats.getEvenCount());
        assertEquals(1, mapStats.getCardinalCountPerSide());
        assertEquals(2, mapStats.getTriangleCountPerSide());
        assertEquals(1, mapStats.getTrapezoidCountPerSide());
    }

    @Test
    void grid131x131With26501365StepsTest() {

        // Arrange & Act
        MapStats mapStats = new MapStats(131, 26501365);

        // Assert basic stats
        assertEquals(131, mapStats.getGridSize());
        assertEquals(26501365, mapStats.getSteps());
        assertEquals(202300, mapStats.getN());

        // Assert steps
        assertEquals(131, mapStats.getCenterSteps());
        assertEquals(130, mapStats.getCardinalSteps());
        assertEquals(64, mapStats.getTriangleSteps());
        assertEquals(195, mapStats.getTrapezoidSteps());

        // Assert count of different maps
        assertEquals(40924885401L, mapStats.getOddCount());
        assertEquals(40925290000L, mapStats.getEvenCount());
        assertEquals(1, mapStats.getCardinalCountPerSide());
        assertEquals(202300, mapStats.getTriangleCountPerSide());
        assertEquals(202299, mapStats.getTrapezoidCountPerSide());
    }
}