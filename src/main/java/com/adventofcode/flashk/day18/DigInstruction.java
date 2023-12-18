package com.adventofcode.flashk.day18;

import com.adventofcode.flashk.common.Vector2Long;
import lombok.Getter;

import java.util.regex.Pattern;

@Getter
public abstract class DigInstruction {

    protected static final Pattern DIG_PATTERN = Pattern.compile("(R|L|D|U) (\\d*) \\(([#]\\w*)\\)");
    protected Vector2Long direction;
    protected long distance;

    public Vector2Long dig(Vector2Long start) {
        Vector2Long directionCopy = new Vector2Long(direction);
        directionCopy.multiply(distance);
        return Vector2Long.transform(start, directionCopy);
    }
}
