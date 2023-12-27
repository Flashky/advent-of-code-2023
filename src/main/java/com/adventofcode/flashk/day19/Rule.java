package com.adventofcode.flashk.day19;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class Rule {

    private static final Pattern FULL_RULE_PATTERN = Pattern.compile("([xmas])([><])(\\d*):(\\w*)");

    public static final char LESS_THAN = '<';

    private char letter;
    private char condition;
    private int value;

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

    // Part 2
    // https://www.reddit.com/r/adventofcode/comments/18mdnfj/2023_day_19_part_2_how_should_i_think_about_this/

    public Range matches(Range initialRange) {
        return initialRange.matchesRange(this);
    }

    public Range unmatches(Range initialRange) {
        return initialRange.unmatchedRange(this);
    }
}