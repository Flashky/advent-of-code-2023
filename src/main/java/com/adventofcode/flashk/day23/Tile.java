package com.adventofcode.flashk.day23;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Tile {

    private int row;
    private int col;
    private char value;

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
