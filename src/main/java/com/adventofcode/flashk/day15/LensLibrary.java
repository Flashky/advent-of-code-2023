package com.adventofcode.flashk.day15;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LensLibrary {

    private static final Pattern STEP_PATTERN = Pattern.compile("([a-z]*)([=-])([1-9]*)");

    private String[] steps;
    private int stepsNumber;
    private List<Map<String,Integer>> boxes = new ArrayList<>();

    public LensLibrary(String input) {
        steps = input.split(",");
        stepsNumber = steps.length;

        for(int i = 0; i < 256; i++) {
            boxes.add(new LinkedHashMap<>());
        }

    }

    public long solveA() {
        long currentValue = 0;
        for(String step : steps) {
            currentValue += hash(step);
        }
        return currentValue;
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

        return calculateBoxes();
    }

    private void removeLens(String label) {
        int boxIndex = hash(label);
        boxes.get(boxIndex).remove(label);
    }

    private void addLens(String label, int focalLength) {
        int boxIndex = hash(label);
        boxes.get(boxIndex).put(label, focalLength);
    }

    private long calculateBoxes() {
        long finalResult = 0;

        for(int boxIndex = 0; boxIndex <boxes.size(); boxIndex++) {
            Collection<Integer> focalLengths = boxes.get(boxIndex).values();
            int slot = 1;
            for(int focalLength : focalLengths) {
                finalResult += (boxIndex+1) * slot * focalLength;
                slot++;
            }
        }

        return finalResult;
    }

    private int hash(String label) {
        int currentValue = 0;
        char[] wordCharacters = label.toCharArray();
        for(char character : wordCharacters) {
            currentValue += (int) character;
            if(currentValue == 10) {
                System.out.println("New character on word = '"+label+"'- Character: "+character);
            }
            currentValue *= 17;
            currentValue %= 256;
        }

        return currentValue;
    }
}
