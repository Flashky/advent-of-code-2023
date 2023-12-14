package com.adventofcode.flashk.day14;

import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;

@Getter
public enum Direction {

    NORTH(Vector2.down(),Vector2.up()),
    WEST(Vector2.left(), Vector2.right()),
    SOUTH(Vector2.up(),Vector2.down()),
    EAST(Vector2.right(), Vector2.left());

    private final Vector2 move;
    private final Vector2 rollback;

    Direction(Vector2 move, Vector2 rollback) {
        this.move = move;
        this.rollback = rollback;
    }

}