package com.adventofcode.flashk.day24;

import lombok.Getter;
import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Hailstone {

    @Getter
    private final Vector3D position;

    @Getter
    private final Vector3D speed;
    
    private final double slope; // m
    private final double intercept; // b
    
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

        // Initialize slope and intercept
        Vector3D position2 = position.add(speed);

        SimpleRegression simpleRegression = new SimpleRegression();
        simpleRegression.addData(position.getX(), position.getY());
        simpleRegression.addData(position2.getX(), position2.getY());

        slope = simpleRegression.getSlope();
        intercept = simpleRegression.getIntercept();

    }

    public boolean intersectsInFuture(Hailstone other, long min, long max) {

        if(slope == other.slope) {
            return false; // Hailstones' paths are parallel; they never intersect.
        }

        // Calculate intersection
        double intersectX = (other.intercept - intercept) / (slope - other.slope);
        double intersectY = slope * intersectX + intercept;

        Vector3D intersection = Vector3D.of(intersectX, intersectY, 0);

        // Intersection might have 4  different cases:
        // - Hailstones' paths crossed in the past for both hailstones. (false)
        // - Hailstones' paths crossed in the past for hailstone A. (false)
        // - Hailstones' paths crossed in the past for hailstone B. (false)
        // - Hailstones' paths cross in the future (true)
        if(isNotInFuture(intersection) || other.isNotInFuture(intersection)) {
            return false;
        }

        // Two cases:
        // - Hailstones' paths cross inside the test area
        // - Hailstones' paths cross outisde the test area
        return isInArea(intersection, min, max);
    }

    private Vector3D createVector3D(String[] coords) {
        return Vector3D.of(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]), Double.parseDouble(coords[2]));
    }

    private boolean isNotInFuture(Vector3D intersection) {

        double deltaX = intersection.getX() - position.getX();
        if(isInvalidSpeedSign(deltaX, speed.getX())){
            return true;
        }

        double deltaY = intersection.getY() - position.getY();
        if(isInvalidSpeedSign(deltaY, speed.getY())){
            return true;
        }

        double deltaZ = intersection.getZ() - position.getZ();
        return (isInvalidSpeedSign(deltaZ, speed.getZ()));
    }

    private boolean isInvalidSpeedSign(double intersectionSpeed, double realSpeed) {
        return ((intersectionSpeed > 0 && realSpeed < 0) || (intersectionSpeed < 0 && realSpeed > 0));
    }

    private boolean isInArea(Vector3D intersection, long min, long max){
        return intersection.getX() >= min &&
                intersection.getX() <= max &&
                intersection.getY() >= min &&
                intersection.getY() <= max;
    }
}
