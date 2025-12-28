package com.adventofcode.flashk.common;

import module java.base;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Collider3D {

    private final Vector3 start;
    private final Vector3 end;

    private long minX;
    private long maxX;
    private long minY;
    private long maxY;
    private long minZ;
    private long maxZ;

    public Collider3D(Vector3 start, Vector3 end) {
        this.start = new Vector3(start);
        this.end = new Vector3(end);

        calculateMinAndMax();
    }

    public Collider3D(Collider3D other) {
        this.start = new Vector3(other.start);
        this.end = new Vector3(other.end);
        calculateMinAndMax();;
    }

    public void transform(Vector3 vector) {

        start.transform(vector);
        end.transform(vector);

        calculateMinAndMax();
    }

    private void calculateMinAndMax() {
        // X coordinate values
        this.minX = Math.min(start.getX(), end.getX());
        this.maxX = Math.max(start.getX(), end.getX());

        // Y coordinate values
        this.minY = Math.min(start.getY(), end.getY());
        this.maxY = Math.max(start.getY(), end.getY());

        // Z coordinate values
        this.minZ = Math.min(start.getZ(), end.getZ());
        this.maxZ = Math.max(start.getZ(), end.getZ());
    }

    // Axis-Aligned Bounding Box
    public boolean collidesWith(Collider3D other) {

        return this.minX <= other.maxX &&
                this.maxX >= other.minX &&
                this.minY <= other.maxY &&
                this.maxY >= other.minY &&
                this.minZ <= other.maxZ &&
                this.maxZ >= other.minZ;

    }

}
