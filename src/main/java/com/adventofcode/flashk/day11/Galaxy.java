package com.adventofcode.flashk.day11;

import com.adventofcode.flashk.common.Vector2;

public class Galaxy {

    private static int galaxyIds = 1;
    private int id;
    private Vector2 position;

    public Galaxy(Vector2 position) {
        id = galaxyIds++;
        this.position = position;
    }

    public int distance(Galaxy other) {
        return Vector2.manhattanDistance(position, other.position);
    }

}
