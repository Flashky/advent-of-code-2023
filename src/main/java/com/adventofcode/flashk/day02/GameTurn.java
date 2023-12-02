package com.adventofcode.flashk.day02;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class GameTurn {

    private final static Pattern RED_PATTERN = Pattern.compile("(\\d+) r");
    private final static Pattern BLUE_PATTERN = Pattern.compile("(\\d+) b");
    private final static Pattern GREEN_PATTERN = Pattern.compile("(\\d+) g");

    private final int redCubes;
    private final int greenCubes;
    private final int blueCubes;

    public GameTurn(String input) {

        // Red
        Matcher redMatcher = RED_PATTERN.matcher(input);
        redCubes = redMatcher.find() ? Integer.parseInt(redMatcher.group(1)) : 0;

        // Green
        Matcher greenMatcher = GREEN_PATTERN.matcher(input);
        greenCubes = greenMatcher.find() ? Integer.parseInt(greenMatcher.group(1)) : 0;

        // Blue
        Matcher blueMatcher = BLUE_PATTERN.matcher(input);
        blueCubes = blueMatcher.find() ? Integer.parseInt(blueMatcher.group(1)) : 0;

    }
}