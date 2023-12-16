package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SplitterTileTest {

    private final Tile vertical = new SplitterVerticalTile(1,1);
    private final Tile horizontal = new SplitterHorizontalTile(1,1);

    @Test
    void nextDirectionsSplitterTileVerticalLeft() {
        List<Vector2> directions = vertical.nextDirections(Vector2.left());

        assertEquals(2, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(0, direction.getX());
        assertEquals(1, direction.getY());

        direction = directions.get(1);
        assertEquals(0, direction.getX());
        assertEquals(-1, direction.getY());
    }

    @Test
    void nextDirectionsSplitterTileVerticalRight() {
        List<Vector2> directions = vertical.nextDirections(Vector2.right());

        assertEquals(2, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(0, direction.getX());
        assertEquals(1, direction.getY());

        direction = directions.get(1);
        assertEquals(0, direction.getX());
        assertEquals(-1, direction.getY());
    }

    @Test
    void nextDirectionsSplitterTileVerticalUp() {
        List<Vector2> directions = vertical.nextDirections(Vector2.down());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(0, direction.getX());
        assertEquals(-1, direction.getY());

    }

    @Test
    void nextDirectionsSplitterTileVerticalDown() {
        List<Vector2> directions = vertical.nextDirections(Vector2.up());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(0, direction.getX());
        assertEquals(1, direction.getY());

    }

    //----

    @Test
    void nextDirectionsSplitterTileHorizontalLeft() {
        List<Vector2> directions = horizontal.nextDirections(Vector2.left());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(-1, direction.getX());
        assertEquals(0, direction.getY());

    }

    @Test
    void nextDirectionsSplitterTileHorizontalRight() {
        List<Vector2> directions = horizontal.nextDirections(Vector2.right());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(1, direction.getX());
        assertEquals(0, direction.getY());
    }

    @Test
    void nextDirectionsSplitterTileHorizontalUp() {
        List<Vector2> directions = horizontal.nextDirections(Vector2.down());

        assertEquals(2, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(-1, direction.getX());
        assertEquals(0, direction.getY());

        direction = directions.get(1);
        assertEquals(1, direction.getX());
        assertEquals(0, direction.getY());

    }

    @Test
    void nextDirectionsSplitterTileHorizontalDown() {
        List<Vector2> directions = horizontal.nextDirections(Vector2.up());

        assertEquals(2, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(-1, direction.getX());
        assertEquals(0, direction.getY());

        direction = directions.get(1);
        assertEquals(1, direction.getX());
        assertEquals(0, direction.getY());

    }
}