package com.adventofcode.flashk.day16;

import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public abstract class Tile {

    // Possible directions in tiles
    public static final Vector2 RIGHT_DIRECTION = Vector2.right();
    public static final Vector2 LEFT_DIRECTION = Vector2.left();
    public static final Vector2 UP_DIRECTION = Vector2.down();
    public static final Vector2 DOWN_DIRECTION = Vector2.up();

    private final char value;
    private final int row;
    private final int col;
    private final Set<Vector2> visitedDirections = new HashSet<>();

    protected Tile(char value, int row, int col) {
        this.value = value;
        this.row = row;
        this.col = col;
    }

    public void visit(Vector2 direction) {
        this.visitedDirections.add(direction);
    }

    public boolean isVisitedOnce() {
        return !visitedDirections.isEmpty();
    }

    public boolean isVisited(Vector2 direction) {
        return visitedDirections.contains(direction);
    }

    public void reset() {
        visitedDirections.clear();
    }

    abstract List<Vector2> nextDirections(Vector2 direction);



}
