package com.adventofcode.flashk.day24;

import lombok.Getter;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.geometry.euclidean.threed.line.Line3D;
import org.apache.commons.geometry.euclidean.threed.line.Lines3D;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.numbers.core.Precision;

public class Hailstone {

    private Vector3D position;
    private Vector3D speed;

    @Getter
    private Line3D trajectory;

    public Hailstone(String input, boolean isPartOne) {
        String[] inputParts = input.split("@");
        String[] posCoords = StringUtils.deleteWhitespace(inputParts[0]).split(",");
        String[] speedCoords = StringUtils.deleteWhitespace(inputParts[1]).split(",");

        if(isPartOne) {
            posCoords[2] = "0";
            speedCoords[2] = "0";
        }

        position = createVector3D(posCoords);
        speed = createVector3D(speedCoords);

        //Precision.DoubleEquivalence equivalence = Precision.doubleEquivalenceOfEpsilon(1e-6);
        Precision.DoubleEquivalence equivalence = Precision.doubleEquivalenceOfEpsilon(1e-10);

        trajectory = Lines3D.fromPointAndDirection(position, speed, equivalence);

    }

    public boolean intersectsInFuture(Hailstone other, long min, long max) {
        Vector3D intersection = trajectory.intersection(other.trajectory);

        // Parallel line
        if(intersection == null) {
            System.out.println("Hailstones's paths are parallel; they never intersect.");
            return false;
        }

        // Check intersection in time
        boolean isInFutureA = isInFuture(intersection);
        boolean isInFutureB = other.isInFuture(intersection);

        if(!isInFutureA && !isInFutureB) {
            System.out.println("Hailstones' paths crossed in the past for both hailstones.");
            return false;
        } else if(!isInFutureA) {
            System.out.println("Hailstones' paths crossed in the past for hailstone A.");
            return false;
        } else if(!isInFutureB) {
            System.out.println("Hailstones' paths crossed in the past for hailstone B.");
            return false;
        }

        boolean isInArea = isInArea(intersection, min, max);
        if(isInArea) {
            System.out.println("Hailstones' paths will cross inside the test area (at x="+intersection.getX()+", y="+intersection.getY()+").");
        } else {
            System.out.println("Hailstones' paths will cross outside the test area (at x="+intersection.getX()+", y="+intersection.getY()+").");
        }

        return isInArea;
    }

    private Vector3D createVector3D(String[] coords) {
        return Vector3D.of(Double.valueOf(coords[0]), Double.valueOf(coords[1]), Double.valueOf(coords[2]));
    }

    private boolean isInFuture(Vector3D intersection) {

        double deltaX = intersection.getX() - position.getX();
        if((deltaX > 0 && speed.getX() < 0) || (deltaX < 0 && speed.getX() > 0)){
            return false;
        }

        double deltaY = intersection.getY() - position.getY();
        if((deltaY > 0 && speed.getY() < 0) || (deltaY < 0 && speed.getY() > 0)){
            return false;
        }

        double deltaZ = intersection.getZ() - position.getZ();
        if((deltaZ > 0 && speed.getZ() < 0) || (deltaZ < 0 && speed.getZ() > 0)){
            return false;
        }

        return true;
    }

    private boolean isInArea(Vector3D intersection, long min, long max){
        return intersection.getX() >= min &&
                intersection.getX() <= max &&
                intersection.getY() >= min &&
                intersection.getY() <= max;
    }

    public boolean samePosition(Hailstone other) {
        return position.equals(other.position);
    }

}
