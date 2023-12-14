package com.adventofcode.flashk.day14;

import com.adventofcode.flashk.common.Collider2D;
import com.adventofcode.flashk.common.Vector2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ParabolicReflectorDish {

    private static final char ROUNDED_ROCK = 'O';
    private static final char CUBED_ROCK = '#';
    private static final char EMPTY = '.';


    private int rows;
    private List<Collider2D> roundedRocks = new ArrayList<>();
    private List<Collider2D> cubedRocks = new ArrayList<>();

    public ParabolicReflectorDish(List<String> inputs) {
        rows = inputs.size();

        int y = rows;
        for(String input : inputs) {

            char[] values = input.toCharArray();
            for(int i = 0; i < values.length; i++) {
                int x = i+1;
                if(values[i] == ROUNDED_ROCK) {
                    roundedRocks.add(new Collider2D(new Vector2(x,y)));
                } else if(values[i] == CUBED_ROCK) {
                    cubedRocks.add(new Collider2D(new Vector2(x,y)));
                }
            }
            y--;
        }

    }

    public long solveA() {

        boolean move;
        do {
            move = moveRocksToNorth();
        } while (move);

        return roundedRocks.stream().map(r -> r.getStart().getY()).reduce(0, Integer::sum);
    }

    private boolean moveRocksToNorth() {
        Vector2 up = Vector2.up();
        Vector2 down = Vector2.down();
        boolean movement = false;
        for(Collider2D roundedRock : roundedRocks) {

            if(roundedRock.getStart().getY() != rows) {
                roundedRock.transform(up);
                if(collidesWithAnyRock(roundedRock)) {
                    roundedRock.transform(down);
                } else {
                    movement = true;
                }
            }
        }
        return movement;
    }
    private boolean collidesWithAnyRock(Collider2D roundedRock) {
        if(roundedRocks.stream().filter(r -> r != roundedRock).anyMatch(r -> r.collidesWith(roundedRock))) {
            return true;
        }

        if(cubedRocks.stream().anyMatch(c -> c.collidesWith(roundedRock))) {
            return true;
        }

        return false;
    }
}
