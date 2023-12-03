package com.adventofcode.flashk.common;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GridUtilTest {

    @Test
    void getAdjacentsIncludingDiagonalsTest() {

        char[][] sample = new char[3][3];
        sample[0] = "abc".toCharArray();
        sample[1] = "def".toCharArray();
        sample[2] = "ghi".toCharArray();

        // First row
        List<Vector2> result = GridUtil.getAdjacentsIncludingDiagonals(sample,0,0);
        assertEquals(3, result.size());
        Set<Character> expectedAdjacentValues = initSet('b', 'd', 'e');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacentsIncludingDiagonals(sample,0,1);
        assertEquals(5, result.size());
        expectedAdjacentValues = initSet('a', 'd', 'e', 'f', 'c');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacentsIncludingDiagonals(sample,0,2);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('b', 'e', 'f');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        // Second row
        result = GridUtil.getAdjacentsIncludingDiagonals(sample,1,0);
        assertEquals(5, result.size());
        expectedAdjacentValues = initSet('a', 'b', 'g', 'e', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacentsIncludingDiagonals(sample,1,1);
        assertEquals(8, result.size());
        expectedAdjacentValues = initSet('a', 'b', 'c', 'd', 'f', 'g', 'h', 'i');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacentsIncludingDiagonals(sample,1,2);
        assertEquals(5, result.size());
        expectedAdjacentValues = initSet('c', 'b', 'e', 'h', 'i');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        // Third row
        result = GridUtil.getAdjacentsIncludingDiagonals(sample,2,0);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('d', 'e', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacentsIncludingDiagonals(sample,2,1);
        assertEquals(5, result.size());
        expectedAdjacentValues = initSet('d', 'g', 'f', 'i', 'e');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacentsIncludingDiagonals(sample,2,2);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('e', 'f', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

    }

    private void testAdjacentValues(Set<Character> expectedAdjacentValues, List<Vector2> result, char[][] array) {
        for(Vector2 vector : result) {
            assertTrue(expectedAdjacentValues.contains(array[vector.getY()][vector.getX()]));
        }
    }

    private Set<Character> initSet(Character... objects) {
        Set<Character> set = new HashSet<Character>();
        Collections.addAll(set, objects);
        return set;
    }
}