package com.adventofcode.flashk.day12.v2;

import static java.lang.IO.println;

import module java.base;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class SpringRecord {

    private static final short REPETITIONS = 5;

    private static final String OPERATIONAL = ".";
    private static final String DAMAGED = "#";
    private static final String UNKNOWN = "?";

    private final String basePattern;
    private final List<Integer> numbers;

    public SpringRecord(String input, boolean unFold) {
        String[] inputParts = input.split(StringUtils.SPACE);
        String rowRecord = inputParts[0];
        String numericPartStr = inputParts[1];

        if(unFold) {
            rowRecord = StringUtils.repeat(rowRecord+ UNKNOWN, REPETITIONS);
            rowRecord = StringUtils.chop(rowRecord);
            numericPartStr = StringUtils.repeat(numericPartStr+",", REPETITIONS);
            StringUtils.chop(numericPartStr);
        }

        basePattern = rowRecord;
        String[] numericPart = numericPartStr.split(",");
        numbers = Arrays.stream(numericPart).map(Integer::valueOf).toList();

    }

    public long count() {
        return count(basePattern, 0, 0);
    }

    private long count(String pattern, int group, int amount) {

        long result = 0;

        if(StringUtils.isBlank(pattern)) {
            if(group >= numbers.size()) {
                return 1;
            } else if((group == numbers.size() - 1) && (amount == numbers.get(group))) {
                return 1; // Is last group
            } else {
                return 0;
            }
        }

        if(group == numbers.size()) {
            return 1;
        }

        String currentValue = pattern.substring(0,1);
        String remainingPattern = pattern.substring(1);
        int expectedAmount = numbers.get(group);

        if(OPERATIONAL.equals(currentValue)) {
            if(amount == expectedAmount) {
                result += count(remainingPattern, group+1, 0); // Go to the next group
            } else if(amount == 0){
                result += count(remainingPattern, group, 0); // Skip until finding the next #
            } else {
                return 0; // Not valid
            }
        } else if(DAMAGED.equals(currentValue)){
            if(amount >= expectedAmount) {
                return 0; // Not valid
            } else {
                result += count(remainingPattern, group, amount+1); // Keep counting group
            }
        } else { // UNKNOWN
            result += count(DAMAGED+remainingPattern, group, amount);
            result += count(OPERATIONAL+remainingPattern, group, amount);
        }

        return result;
    }

}
