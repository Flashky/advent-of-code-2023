package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class EmptyTile extends Tile {

    private static final char EMPTY = '.';

    protected EmptyTile(char value, int row, int col) {
        super(value, row, col);
        if(value != EMPTY) {
            throw new IllegalArgumentException("Value is not an empty tile: '.'");
        }
    }

    @Override
    public List<Vector2> nextDirections(Vector2 direction) {
        return List.of(new Vector2(direction));
    }
}
