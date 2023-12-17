package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class TileStatus implements Comparable<TileStatus> {

    private Tile tile;
    private Vector2 direction;
    private int length;

    @Override
    public int compareTo(TileStatus other) {
        return tile.compareTo(other.tile);
    }
}
