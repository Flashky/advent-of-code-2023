package com.adventofcode.flashk.day21;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Cell {

    private static final char ROCK = '#';
    private static final char GARDEN_PLOT = '.';

    private final int row;
    private final int col;
    private final char value;

    @Setter
    private boolean visited = false;

    @Setter
    private long step = 0;

    public Cell(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = (value == 'S') ? GARDEN_PLOT : value;
    }

    public boolean isRock() {
        return value == ROCK;
    }

    public boolean isEven() {
        return step % 2 == 0;
    }

    public void print(boolean printEven) {
        if(isRock()) {
            IO.print(ROCK);
        } else if (isVisited() && printEven == isEven()) {
            IO.print("O");
        } else {
            IO.print(GARDEN_PLOT);
        }
    }
}