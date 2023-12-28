package com.adventofcode.flashk.day23;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
public class Tile implements Comparable<Tile> {

    private int row;
    private int col;
    private char value;

    @Setter
    private int totalSteps = 0;

    @Setter
    private boolean visited = false;

    @Setter
    private int steps = 0;

    public Tile(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
    }


    @Override
    public int compareTo(Tile o) {
        return Integer.compare(totalSteps, o.totalSteps);
    }
}
