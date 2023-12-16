package com.adventofcode.flashk.day16;

public abstract class SplitterTile extends Tile {

    private static final char VERTICAL = '|';
    private static final char HORIZONTAL = '-';

    protected SplitterTile(char value, int row, int col) {
        super(value, row, col);
        if(value != VERTICAL && value != HORIZONTAL) {
            throw new IllegalArgumentException("Tile must be a splitter");
        }
    }

}
