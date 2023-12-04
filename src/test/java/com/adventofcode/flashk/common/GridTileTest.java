package com.adventofcode.flashk.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTileTest {

    @Test
    void getRow() {
        GridTile<String> tile = new GridTile<>(1,3,"foo");
        assertEquals(1, tile.getRow());
    }

    @Test
    void getCol() {
        GridTile<String> tile = new GridTile<>(1,3,"foo");
        assertEquals(3, tile.getCol());
    }

    @Test
    void getValue() {
        GridTile<String> tile = new GridTile<>(1,3,"foo");
        assertEquals("foo", tile.getValue());
    }

    @Test
    void setValue() {
        GridTile<String> tile = new GridTile<>(1,3,"foo");
        tile.setValue("bar");
        assertEquals("bar", tile.getValue());
    }
}