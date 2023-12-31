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

    @Test
    void getAdjacentsTest() {
        char[][] sample = new char[3][3];
        sample[0] = "abc".toCharArray();
        sample[1] = "def".toCharArray();
        sample[2] = "ghi".toCharArray();

        // First row
        List<Vector2> result = GridUtil.getAdjacents(sample,0,0);
        assertEquals(2, result.size());
        Set<Character> expectedAdjacentValues = initSet('b', 'd');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,0,1);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('a', 'e', 'c');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,0,2);
        assertEquals(2, result.size());
        expectedAdjacentValues = initSet('b', 'f');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        // Second row
        result = GridUtil.getAdjacents(sample,1,0);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('a', 'e', 'g');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,1,1);
        assertEquals(4, result.size());
        expectedAdjacentValues = initSet('b', 'd', 'f', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,1,2);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('c', 'e', 'i');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        // Third row
        result = GridUtil.getAdjacents(sample,2,0);
        assertEquals(2, result.size());
        expectedAdjacentValues = initSet('d', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,2,1);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('g', 'e', 'i');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,2,2);
        assertEquals(2, result.size());
        expectedAdjacentValues = initSet('h', 'f');
        testAdjacentValues(expectedAdjacentValues, result, sample);
    }
/*
    @Test
    void getAdjacentsIncludingDiagonalsIntTest() {

        int[][] sample = new int[3][];
        sample[0] = new int[]{1,2,3};
        sample[1] = new int[]{4,5,6};
        sample[2] = new int[]{7,8,9};

        // First row
        List<Vector2> result = GridUtil.getAdjacentsIncludingDiagonals(sample,0,0);
        assertEquals(3, result.size());
        Set<Integer> expectedAdjacentValues = initSet(2, 4, 5);
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

    @Test
    void getAdjacentsIntTest() {
        char[][] sample = new char[3][3];
        sample[0] = "abc".toCharArray();
        sample[1] = "def".toCharArray();
        sample[2] = "ghi".toCharArray();

        // First row
        List<Vector2> result = GridUtil.getAdjacents(sample,0,0);
        assertEquals(2, result.size());
        Set<Character> expectedAdjacentValues = initSet('b', 'd');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,0,1);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('a', 'e', 'c');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,0,2);
        assertEquals(2, result.size());
        expectedAdjacentValues = initSet('b', 'f');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        // Second row
        result = GridUtil.getAdjacents(sample,1,0);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('a', 'e', 'g');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,1,1);
        assertEquals(4, result.size());
        expectedAdjacentValues = initSet('b', 'd', 'f', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,1,2);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('c', 'e', 'i');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        // Third row
        result = GridUtil.getAdjacents(sample,2,0);
        assertEquals(2, result.size());
        expectedAdjacentValues = initSet('d', 'h');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,2,1);
        assertEquals(3, result.size());
        expectedAdjacentValues = initSet('g', 'e', 'i');
        testAdjacentValues(expectedAdjacentValues, result, sample);

        result = GridUtil.getAdjacents(sample,2,2);
        assertEquals(2, result.size());
        expectedAdjacentValues = initSet('h', 'f');
        testAdjacentValues(expectedAdjacentValues, result, sample);
    }
*/
    private void testAdjacentValues(Set<Character> expectedAdjacentValues, List<Vector2> result, char[][] array) {
        for(Vector2 vector : result) {
            assertTrue(expectedAdjacentValues.contains(array[vector.getY()][vector.getX()]));
        }
    }

    private void testAdjacentValues(Set<Character> expectedAdjacentValues, List<Vector2> result, int[][] array) {
        for(Vector2 vector : result) {
            assertTrue(expectedAdjacentValues.contains(array[vector.getY()][vector.getX()]));
        }
    }

    private Set<Character> initSet(Character... objects) {
        Set<Character> set = new HashSet<>();
        Collections.addAll(set, objects);
        return set;
    }

    private Set<Integer> initSet(Integer... objects) {
        Set<Integer> set = new HashSet<>();
        Collections.addAll(set, objects);
        return set;
    }


}