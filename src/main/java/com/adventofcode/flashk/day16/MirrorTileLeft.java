package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

public class MirrorTileLeft extends MirrorTile {

    private static final char LEFT = '\\';

    public MirrorTileLeft(char value, int row, int col) {
        super(value, row, col);
        if(value != LEFT) {
            throw new IllegalArgumentException("Value is not a left mirror: '\\'");
        }
    }

    @Override
    protected Vector2 reflect(Vector2 direction) {

        Vector2 newDirection = new Vector2(direction);

        if(RIGHT_DIRECTION.equals(direction) || LEFT_DIRECTION.equals(direction)) {
            newDirection.rotateLeft();
            return newDirection;
        }

        newDirection.rotateRight();
        return newDirection;
    }
}
