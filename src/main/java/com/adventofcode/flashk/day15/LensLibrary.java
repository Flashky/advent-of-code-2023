package com.adventofcode.flashk.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LensLibrary {

    private static final Pattern STEP_PATTERN = Pattern.compile("([a-z]*)([=-])([1-9]*)");

    private final String[] steps;
    private final List<Map<String,Integer>> boxes = new ArrayList<>();

    public LensLibrary(String input) {
        steps = input.split(",");

        for(int i = 0; i < 256; i++) {
            boxes.add(new LinkedHashMap<>());
        }

    }

    public int solveA() {
        return Arrays.stream(steps).map(this::hash).reduce(0, Integer::sum);
    }

    public long solveB() {
        for(String step : steps) {
            Matcher matcher = STEP_PATTERN.matcher(step);
            if(matcher.find()) {
                String label = matcher.group(1);
                String operation = matcher.group(2);
                if("-".equals(operation)) {
                    removeLens(label);
                } else {
                    int focalLength = Integer.parseInt(matcher.group(3));
                    addLens(label, focalLength);
                }
            }
        }

        return focusingPower();
    }

    private void removeLens(String label) {
        int boxIndex = hash(label);
        boxes.get(boxIndex).remove(label);
    }

    private void addLens(String label, int focalLength) {
        int boxIndex = hash(label);
        boxes.get(boxIndex).put(label, focalLength);
    }

    private int focusingPower() {
        int focusingPower = 0;

        for(int boxIndex = 0; boxIndex < boxes.size(); boxIndex++) {
            focusingPower += focusingPower(boxIndex);
        }

        return focusingPower;
    }

    private int focusingPower(int boxIndex) {
        int focusingPower = 0;
        Collection<Integer> focalLengths = boxes.get(boxIndex).values();

        int slot = 1;
        int boxValue = boxIndex + 1;
        for(int focalLength : focalLengths) {
            focusingPower += boxValue * slot++ * focalLength;
        }
        
        return focusingPower;
    }

    private int hash(String label) {
        int currentValue = 0;
        char[] wordCharacters = label.toCharArray();
        for(char character : wordCharacters) {
            currentValue += character;
            currentValue *= 17;
            currentValue %= 256;
        }

        return currentValue;
    }
}
