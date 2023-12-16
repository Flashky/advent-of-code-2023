package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class SplitterHorizontalTile extends Tile{

    public SplitterHorizontalTile(int row, int col) {
        super(row, col);
    }

    @Override
    public List<Vector2> nextDirections(Vector2 direction) {

        if(RIGHT_DIRECTION.equals(direction) || LEFT_DIRECTION.equals(direction)) {
            return List.of(direction);
        }

        return List.of(Vector2.left(), Vector2.right());

    }
}
