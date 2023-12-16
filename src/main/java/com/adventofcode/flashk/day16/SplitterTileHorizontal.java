package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class SplitterTileHorizontal extends SplitterTile{

    private static final char HORIZONTAL = '-';

    protected SplitterTileHorizontal(char value, int row, int col) {
        super(value, row, col);
        if(value != HORIZONTAL) {
            throw new IllegalArgumentException("Value is not a horizontal splitter: '-'");
        }
    }

    @Override
    List<Vector2> nextDirections(Vector2 direction) {

        if(RIGHT_DIRECTION.equals(direction) || LEFT_DIRECTION.equals(direction)) {
            return List.of(direction);
        }

        return List.of(Vector2.left(), Vector2.right());

    }
}
