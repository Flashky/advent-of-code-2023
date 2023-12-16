package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MirrorTileTest {

    private final Tile left = new MirrorLeftTile(1,1);
    private final Tile right = new MirrorRightTile(1,1);

    @Test
    void reflectLeftMirrorWithRightDirection() {
        List<Vector2> directions = left.nextDirections(Vector2.right());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);
        assertEquals(0, direction.getX());
        assertEquals(1, direction.getY());
    }

    @Test
    void reflectLeftMirrorWithLeftDirection() {
        List<Vector2> directions = left.nextDirections(Vector2.left());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(0, direction.getX());
        assertEquals(-1, direction.getY());
    }

    @Test
    void reflectLeftMirrorWithUpDirection() {
        // Up means down (-1) in the array so Vector2.down()
        List<Vector2> directions = left.nextDirections(Vector2.down());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(-1, direction.getX());
        assertEquals(0, direction.getY());
    }

    @Test
    void reflectLeftMirrorWithDownDirection() {
        // Down here means up (+1) in the array so Vector2.up()
        List<Vector2> directions = left.nextDirections(Vector2.up());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(1, direction.getX());
        assertEquals(0, direction.getY());
    }

    //-----

    @Test
    void reflectRightMirrorWithRightDirection() {
        List<Vector2> directions = right.nextDirections(Vector2.right());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(0, direction.getX());
        assertEquals(-1, direction.getY());
    }

    @Test
    void reflectRightMirrorWithLeftDirection() {
        List<Vector2> directions = right.nextDirections(Vector2.left());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(0, direction.getX());
        assertEquals(1, direction.getY());
    }

    @Test
    void reflectRightMirrorWithUpDirection() {
        // Up means down (-1) in the array so Vector2.down()
        List<Vector2> directions = right.nextDirections(Vector2.down());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(1, direction.getX());
        assertEquals(0, direction.getY());
    }

    @Test
    void reflectRightMirrorWithDownDirection() {
        // Down here means up (+1) in the array so Vector2.up()
        List<Vector2> directions = right.nextDirections(Vector2.up());

        assertEquals(1, directions.size());
        Vector2 direction = directions.get(0);

        assertEquals(-1, direction.getX());
        assertEquals(0, direction.getY());
    }

}
