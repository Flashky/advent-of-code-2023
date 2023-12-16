package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;

import java.util.List;

@Getter
public abstract class MirrorTile extends Tile {

    protected MirrorTile(char value, int row, int col) {
        super(value, row, col);
    }

    @Override
    public List<Vector2> nextDirections(Vector2 direction) {
        return List.of(reflect(direction));
    }

    abstract Vector2 reflect(Vector2 direction);
}
