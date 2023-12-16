package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class SplitterVerticalTile extends Tile {

    public SplitterVerticalTile(int row, int col) {
        super(row, col);
    }

    @Override
    public List<Vector2> nextDirections(Vector2 direction) {

        if(UP_DIRECTION.equals(direction) || DOWN_DIRECTION.equals(direction)) {
            return List.of(direction);
        }

        return List.of(Vector2.up(), Vector2.down());
    }
}
