package com.adventofcode.flashk.day01;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Trebuchet {

    private final List<String> inputs;
    private final String[] numberWords = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    private final String[] numberDigits = {"o1e", "t2o", "t3e", "f4r", "f5e", "s6x", "s7n", "e8t", "n9e"};

    public Trebuchet(List<String> inputs) {
        this.inputs = inputs;
    }

    public int solveA() {
        return inputs.stream()
                .map(this::lineValue)
                .reduce(0,Math::addExact);
    }

    public int solveB() {
        return inputs.stream()
                .map(this::replaceWordsWithNumber)
                .map(this::lineValue)
                .reduce(0,Math::addExact);
    }

    private int lineValue(String line) {
        String digits = StringUtils.getDigits(line);
        String number = String.valueOf(digits.charAt(0)) + digits.charAt(digits.length() - 1);

        return Integer.parseInt(number);
    }

    private String replaceWordsWithNumber(String line){
        return StringUtils.replaceEachRepeatedly(line, numberWords, numberDigits);
    }
}