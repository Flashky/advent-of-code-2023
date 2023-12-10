package com.adventofcode.flashk.day10;

import com.adventofcode.flashk.common.Vector2;

import java.util.NoSuchElementException;

public enum Pipe {
    VERTICAL('|'),
    HORIZONTAL('-'),
    NORTH_EAST('L'),
    NORTH_WEST('J'),
    SOUTH_WEST('7'),
    SOUTH_EAST('F');


    private final char value;

    Pipe(char value) {
        this.value = value;
    }

    public Vector2 direction(Vector2 currentDirection) {
        switch(value) {
            case '|', '-': return currentDirection;
            case 'L': return currentDirection.equals(Vector2.up()) ? Vector2.right() : Vector2.down();
            case 'J': return currentDirection.equals(Vector2.up()) ? Vector2.left() : Vector2.down();
            case '7': return currentDirection.equals(Vector2.down()) ? Vector2.left() : Vector2.up();
            case 'F': return currentDirection.equals(Vector2.down()) ? Vector2.right() : Vector2.up();
            default: throw new NoSuchElementException("No enum value for pipe");
        }
    }

    public static Pipe of(char value) {
        return switch(value) {
            case '|' -> VERTICAL;
            case '-' -> HORIZONTAL;
            case 'L' -> NORTH_EAST;
            case 'J' -> NORTH_WEST;
            case '7' -> SOUTH_WEST;
            case 'F' -> SOUTH_EAST;
            default -> throw new NoSuchElementException("No enum value for pipe");
        };
    }
}
