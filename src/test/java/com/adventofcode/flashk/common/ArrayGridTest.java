package com.adventofcode.flashk.common;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArrayGridTest {

    private Grid<Character> characterGrid;
    private Map<Character, GridTile<Character>> gridTiles = new HashMap<>();
    @BeforeEach
    void setUp() {

        char[][] array = new char[3][];
        array[0] = new char[]{'a','b','c'};
        array[1] = new char[]{'d','e','f'};
        array[2] = new char[]{'g','h','i'};

        characterGrid = GridUtil.asGrid(array);
        gridTiles.put('a', gridTile(0,0,'a'));
        gridTiles.put('b', gridTile(0,1,'b'));
        gridTiles.put('c', gridTile(0,2,'c'));
        gridTiles.put('d', gridTile(1,0,'d'));
        gridTiles.put('e', gridTile(1,1,'e'));
        gridTiles.put('f', gridTile(1,2,'f'));
        gridTiles.put('g', gridTile(2,0,'g'));
        gridTiles.put('h', gridTile(2,1,'h'));
        gridTiles.put('i', gridTile(2,2,'i'));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void rowsTest() {
        assertEquals(3, characterGrid.rows());
    }

    @Test
    void colsTest() {
        assertEquals(3, characterGrid.cols());
    }

    @Test
    void getTest() {
        assertEquals('a', characterGrid.get(0,0));
        assertEquals('b', characterGrid.get(0,1));
        assertEquals('c', characterGrid.get(0,2));
        assertEquals('d', characterGrid.get(1,0));
        assertEquals('e', characterGrid.get(1,1));
        assertEquals('f', characterGrid.get(1,2));
        assertEquals('g', characterGrid.get(2,0));
        assertEquals('h', characterGrid.get(2,1));
        assertEquals('i', characterGrid.get(2,2));
    }

    @Test
    void setTest() {
        characterGrid.set(1,1, 'x');
        assertEquals('x', characterGrid.get(1,1));
    }

    @Test
    void getAdjacentValuesDiagonalFalseTest() {

        // (0,0)
        Set<Character> adjacentValues = characterGrid.getAdjacentValues(0,0, false);
        assertEquals(2, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('b','d')));

        // (0,1)
        adjacentValues = characterGrid.getAdjacentValues(0,1, false);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('a','c','e')));

        // (0,2)
        adjacentValues = characterGrid.getAdjacentValues(0,2, false);
        assertEquals(2, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('b','f')));

        // (1,0)
        adjacentValues = characterGrid.getAdjacentValues(1,0, false);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('a','e','g')));

        // (1,1)
        adjacentValues = characterGrid.getAdjacentValues(1,1, false);
        assertEquals(4, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('b','d','f','h')));

        // (1,2)
        adjacentValues = characterGrid.getAdjacentValues(1,2, false);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('c','e','i')));

        // (2,0)
        adjacentValues = characterGrid.getAdjacentValues(2,0, false);
        assertEquals(2, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('d','h')));

        // (2,1)
        adjacentValues = characterGrid.getAdjacentValues(2,1, false);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('e','g','i')));

        // (2,2)
        adjacentValues = characterGrid.getAdjacentValues(2,2, false);
        assertEquals(2, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('f','h')));

    }

    @Test
    void getAdjacentValuesDiagonalTrueTest() {

        // (0,0)
        Set<Character> adjacentValues = characterGrid.getAdjacentValues(0,0, true);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('b','d','e')));

        // (0,1)
        adjacentValues = characterGrid.getAdjacentValues(0,1, true);
        assertEquals(5, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('a','c','d','e','f')));

        // (0,2)
        adjacentValues = characterGrid.getAdjacentValues(0,2, true);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('b','e','f')));

        // (1,0)
        adjacentValues = characterGrid.getAdjacentValues(1,0, true);
        assertEquals(5, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('a','b','e','g','h')));

        // (1,1)
        adjacentValues = characterGrid.getAdjacentValues(1,1, true);
        assertEquals(8, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('a','b','c','d','f','g','h')));

        // (1,2)
        adjacentValues = characterGrid.getAdjacentValues(1,2, true);
        assertEquals(5, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('b','c','e','h','i')));

        // (2,0)
        adjacentValues = characterGrid.getAdjacentValues(2,0, true);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('d','e','h')));

        // (2,1)
        adjacentValues = characterGrid.getAdjacentValues(2,1, true);
        assertEquals(5, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('d','e','f','g','i')));

        // (2,2)
        adjacentValues = characterGrid.getAdjacentValues(2,2, true);
        assertEquals(3, adjacentValues.size());
        assertTrue(adjacentValues.containsAll(Set.of('e','f','h')));

    }

    @Test
    void getAdjacentTilesDiagonalFalseTest() {

        // (0,0)
        Set<GridTile<Character>> adjacentTiles = characterGrid.getAdjacentTiles(0,0, false);
        assertEquals(2, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('b','d')));

        // (0,1)
        adjacentTiles = characterGrid.getAdjacentTiles(0,1, false);
        assertEquals(3, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('a','c','e')));

        // (0,2)
        adjacentTiles = characterGrid.getAdjacentTiles(0,2, false);
        assertEquals(2, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('b','f')));

        // (1,0)
        adjacentTiles = characterGrid.getAdjacentTiles(1,0, false);
        assertEquals(3, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('a','e','g')));

        // (1,1)
        adjacentTiles = characterGrid.getAdjacentTiles(1,1, false);
        assertEquals(4, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('b','d','f','h')));

        // (1,2)
        adjacentTiles = characterGrid.getAdjacentTiles(1,2, false);
        assertEquals(3, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('c','e','i')));

        // (2,0)
        adjacentTiles = characterGrid.getAdjacentTiles(2,0, false);
        assertEquals(2, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('d','h')));

        // (2,1)
        adjacentTiles = characterGrid.getAdjacentTiles(2,1, false);
        assertEquals(3, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('e','g','i')));

        // (2,2)
        adjacentTiles = characterGrid.getAdjacentTiles(2,2, false);
        assertEquals(2, adjacentTiles.size());
        assertTrue(adjacentTiles.containsAll(setGridTileOf('f','h')));

    }

    @Test
    void getAdjacentTilesDiagonalTrueTest() {

        // (0,0)
        Set<GridTile<Character>> tiles = characterGrid.getAdjacentTiles(0,0, true);
        assertEquals(3, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('b','d','e')));

        // (0,1)
        tiles = characterGrid.getAdjacentTiles(0,1, true);
        assertEquals(5, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('a','c','d','e','f')));

        // (0,2)
        tiles = characterGrid.getAdjacentTiles(0,2, true);
        assertEquals(3, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('b','e','f')));

        // (1,0)
        tiles = characterGrid.getAdjacentTiles(1,0, true);
        assertEquals(5, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('a','b','e','g','h')));

        // (1,1)
        tiles = characterGrid.getAdjacentTiles(1,1, true);
        assertEquals(8, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('a','b','c','d','f','g','h')));

        // (1,2)
        tiles = characterGrid.getAdjacentTiles(1,2, true);
        assertEquals(5, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('b','c','e','h','i')));

        // (2,0)
        tiles = characterGrid.getAdjacentTiles(2,0, true);
        assertEquals(3, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('d','e','h')));

        // (2,1)
        tiles = characterGrid.getAdjacentTiles(2,1, true);
        assertEquals(5, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('d','e','f','g','i')));

        // (2,2)
        tiles = characterGrid.getAdjacentTiles(2,2, true);
        assertEquals(3, tiles.size());
        assertTrue(tiles.containsAll(setGridTileOf('e','f','h')));

    }

    private GridTile<Character> gridTile(int row, int col, Character value) {
        return new GridTile<>(row,col,value);
    }

    private Set<GridTile<Character>> setGridTileOf(char... characters) {
        Set<GridTile<Character>> set = new HashSet<>();
        for(char character : characters) {
            set.add(gridTiles.get(character));
        }

        return set;
    }
}