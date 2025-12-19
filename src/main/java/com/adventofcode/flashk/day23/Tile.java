package com.adventofcode.flashk.day23;

import static java.lang.IO.println;

import module java.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Tile {

    private final int row;
    private final int col;
    private final char value;

    @Setter
    private boolean visited = false;

    @Setter
    private int steps = 0;

    public Tile(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }


}
