package com.adventofcode.flashk.day22;

import module java.base;
import com.adventofcode.flashk.common.Collider3D;
import com.adventofcode.flashk.common.Vector3;

public class Brick {

    private final Vector3 position;
    private final Collider3D collider;

    public Brick(String input) {
        String[] coordinates = input.split("~");

        Vector3 startCoordinate = new Vector3(coordinates[0]);
        Vector3 endCoordinate = new Vector3(coordinates[1]);
        collider = new Collider3D(startCoordinate, endCoordinate);
        position = new Vector3(Math.min(startCoordinate.getX(), endCoordinate.getX()),
                                Math.min(startCoordinate.getY(), endCoordinate.getY()),
                                Math.min(startCoordinate.getZ(), endCoordinate.getZ()));
    }

    public void move(Vector3 direction) {
        position.transform(direction);
        collider.transform(direction);
    }

    public boolean collidesWith(Brick other) {
        if(other == this) {
            return false;
        }
        return collider.collidesWith(other.collider);
    }

    // TODO the right thing would be to return <= 1
    public boolean isAtGround() {
        return position.getZ() < 1;
    }
}
