package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class MirrorLeftTile extends Tile {

    public MirrorLeftTile(int row, int col) {
        super(row, col);
    }

    @Override
    public List<Vector2> nextDirections(Vector2 direction) {

        Vector2 newDirection = new Vector2(direction);

        if(RIGHT_DIRECTION.equals(direction) || LEFT_DIRECTION.equals(direction)) {
            newDirection.rotateLeft();
        } else {
            newDirection.rotateRight();
        }

        return List.of(newDirection);

    }

}
