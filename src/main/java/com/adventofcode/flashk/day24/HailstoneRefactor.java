package com.adventofcode.flashk.day24;

import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;
public class HailstoneRefactor {

    private Vector3D position;
    private Vector3D speed;
    
    private double slope; // m
    private double intercept; // b
    
    public HailstoneRefactor(String input, boolean isPartOne) {
        String[] inputParts = input.split("@");
        String[] posCoords = StringUtils.deleteWhitespace(inputParts[0]).split(",");
        String[] speedCoords = StringUtils.deleteWhitespace(inputParts[1]).split(",");

        if(isPartOne) {
            posCoords[2] = "0";
            speedCoords[2] = "0";
        }

        position = createVector3D(posCoords);
        speed = createVector3D(speedCoords);

        // Initialize slope and intercept
        Vector3D position2 = position.add(speed);

        SimpleRegression simpleRegression = new SimpleRegression();
        simpleRegression.addData(position.getX(), position.getY());
        simpleRegression.addData(position2.getX(), position2.getY());

        slope = simpleRegression.getSlope();
        intercept = simpleRegression.getIntercept();

    }

    public boolean intersectsInFuture(HailstoneRefactor other, long min, long max) {

        // Parallel trajectories
        if(slope == other.slope) {
            System.out.println("Hailstones's paths are parallel; they never intersect.");
            return false;
        }

        // Calculate intersection
        double intersectX = (other.intercept - intercept) / (slope - other.slope);
        double intersectY = slope * intersectX + intercept;

        Vector3D intersection = Vector3D.of(intersectX, intersectY, 0);

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
}
