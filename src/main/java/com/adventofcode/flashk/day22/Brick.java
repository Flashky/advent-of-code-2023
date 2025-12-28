package com.adventofcode.flashk.day22;

import module java.base;
import com.adventofcode.flashk.common.Collider3D;
import com.adventofcode.flashk.common.Vector3;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Brick {

    private Vector3 initialPosition;
    private Collider3D initialCollider;
    private Vector3 position;
    private Collider3D collider;

    public Brick(String input) {
        String[] coordinates = input.split("~");

        Vector3 startCoordinate = new Vector3(coordinates[0]);
        Vector3 endCoordinate = new Vector3(coordinates[1]);
        collider = new Collider3D(startCoordinate, endCoordinate);
        position = new Vector3(Math.min(startCoordinate.getX(), endCoordinate.getX()),
                                Math.min(startCoordinate.getY(), endCoordinate.getY()),
                                Math.min(startCoordinate.getZ(), endCoordinate.getZ()));

        initialPosition = new Vector3(position);
        initialCollider = new Collider3D(collider);
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

    public long getMinZ() {
        return position.getZ();
    }

    public void commit() {
        initialPosition = new Vector3(position);
        initialCollider = new Collider3D(collider);
    }

    public void reset() {
        position = new Vector3(initialPosition);
        collider = new Collider3D(initialCollider);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Brick brick)) return false;

        return new EqualsBuilder().append(initialPosition, brick.initialPosition).append(initialCollider, brick.initialCollider).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(initialPosition).append(initialCollider).toHashCode();
    }
}
