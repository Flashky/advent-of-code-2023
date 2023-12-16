package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;

import java.util.List;

public class EmptyTile extends Tile {

    protected EmptyTile(int row, int col) {
        super(row, col);
    }

    @Override
    public List<Vector2> nextDirections(Vector2 direction) {
        return List.of(new Vector2(direction));
    }
}
