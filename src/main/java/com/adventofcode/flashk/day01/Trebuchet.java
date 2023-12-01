package com.adventofcode.flashk.day01;

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
        List<String> result = line.chars()
                .filter(Character::isDigit)
                .mapToObj(c -> (char) c)
                .map(Object::toString)
                .toList();

        String first = result.get(0);
        String last = result.get(result.size()-1);

        String number = first + last;
        return Integer.parseInt(number);
    }
    private String replaceWordsWithNumber(String line){

        String replaceLine = line;
        for(int i = 0; i < numberWords.length; i++) {
            replaceLine = replaceLine.replace(numberWords[i], numberDigits[i]);
        }
        return replaceLine;

        // This alternative should work but it doesn't:
        //return StringUtils.replaceEach(line, numberWords, numberDigits);
    }
}
