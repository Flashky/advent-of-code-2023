package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class SplitterTileVertical extends SplitterTile {

    private static final char VERTICAL = '|';

    protected SplitterTileVertical(char value, int row, int col) {
        super(value, row, col);
        if(value != VERTICAL) {
            throw new IllegalArgumentException("Value is not a vertical splitter: '|'");
        }
    }

    @Override
    List<Vector2> nextDirections(Vector2 direction) {
        if(UP_DIRECTION.equals(direction) || DOWN_DIRECTION.equals(direction)) {
            return List.of(direction);
        }

        return List.of(Vector2.up(), Vector2.down());
    }
}
