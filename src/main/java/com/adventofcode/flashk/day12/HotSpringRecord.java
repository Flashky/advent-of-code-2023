package com.adventofcode.flashk.day12;

import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class HotSpringRecord {

    private static final String OPERATIONAL = ".";
    private static final String DAMAGED = "#";
    private static final String UNKOWN = "?";

    private String row;
    private List<Integer> numbers;

    public HotSpringRecord(String input, boolean unFold) {
        String[] inputParts = input.split(StringUtils.SPACE);
        String rowRecord = inputParts[0];
        String numericPartStr = inputParts[1];

        if(unFold) {
            row = StringUtils.repeat(rowRecord+UNKOWN, 5);
            row = StringUtils.removeEnd(row, UNKOWN);
            numericPartStr = StringUtils.repeat(numericPartStr+",", 5);
            numericPartStr = StringUtils.removeEnd(numericPartStr, ",");
            String[] numericPart = numericPartStr.split(",");
            numbers = Arrays.stream(numericPart).map(Integer::valueOf).toList();
        } else {
            row = inputParts[0];
            String[] numericPart = numericPartStr.split(",");
            numbers = Arrays.stream(numericPart).map(Integer::valueOf).toList();
        }


    }

    public long countArrangements() {

        // String pattern generation
        String generationPattern = row.replace(OPERATIONAL, "[.]");
        generationPattern = generationPattern.replace(DAMAGED, "[#]");
        generationPattern = generationPattern.replace(UNKOWN, "[.#]");

        Generex generex = new Generex(generationPattern);
        List<String> possibleRow = generex.getAllMatchedStrings();

        return possibleRow.stream().filter(this::isValid).count();

    }


    private boolean isValid(String arrangement) {

        String[] damagedGroups = arrangement
                .replace(OPERATIONAL, StringUtils.SPACE)
                .stripLeading()
                .split("\\s+");

        if(damagedGroups.length != numbers.size()) {
            return false;
        }

        for(int i = 0; i < damagedGroups.length; i++) {
            if(damagedGroups[i].length() != numbers.get(i)) {
                return false;
            }
        }
        return true;
    }
}
