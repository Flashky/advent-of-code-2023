package com.adventofcode.flashk.day19;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {

    private static final Pattern FULL_RULE_PATTERN = Pattern.compile("([xmas])([><])(\\d*):(\\w*)");

    private static final char LESS_THAN = '<';

    private char letter;
    private char condition;
    private int value;

    @Getter
    private String destinationWorkflow;

    private boolean bypassRule;

    public Rule(String input) {
        Matcher matcher = FULL_RULE_PATTERN.matcher(input);
        if(matcher.find()) {
            letter = matcher.group(1).charAt(0);
            condition = matcher.group(2).charAt(0);
            value = Integer.parseInt(matcher.group(3));
            destinationWorkflow = matcher.group(4);
            bypassRule = false;

        } else {
            // Direct workflow
            destinationWorkflow = input;
            bypassRule = true;
        }
    }

    public boolean matches(Part part) {

        if(bypassRule) {
            return true;
        }

        if(LESS_THAN == condition) {
            return part.getRating(letter) < value;
        }

        return part.getRating(letter) > value;
    }
}
