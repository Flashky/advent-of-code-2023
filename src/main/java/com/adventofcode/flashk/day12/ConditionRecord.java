package com.adventofcode.flashk.day12;

import module java.base;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class ConditionRecord {

    private static final short REPETITIONS = 5;

    private static final String OPERATIONAL = ".";
    private static final String DAMAGED = "#";
    private static final String UNKNOWN = "?";

    private final String basePattern;
    private final List<Integer> numbers;

    private final Map<CountStatus, Long> memo = new HashMap<>();

    public ConditionRecord(String input, boolean unFold) {
        String[] inputParts = input.split(StringUtils.SPACE);
        String rowRecord = inputParts[0];
        String numericPartStr = inputParts[1];

        if(unFold) {
            rowRecord = StringUtils.repeat(rowRecord + UNKNOWN, REPETITIONS);
            rowRecord = StringUtils.chop(rowRecord);
            numericPartStr = StringUtils.repeat(numericPartStr + ",", REPETITIONS);
            numericPartStr = StringUtils.chop(numericPartStr);
        }

        basePattern = rowRecord;
        String[] numericPart = numericPartStr.split(",");
        numbers = Arrays.stream(numericPart).map(Integer::valueOf).toList();

    }

    public long count() {
        return count(basePattern, 0, 0);
    }

    private long count(String pattern, int group, int amount) {

        CountStatus countStatus = new CountStatus(pattern, group, amount);

        // Memoization - Check cache first
        if(memo.containsKey(countStatus)) {
            return memo.get(countStatus);
        }

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

        String currentValue = pattern.substring(0,1);
        String remainingPattern = pattern.substring(1);

        if(group == numbers.size()) {
            return remainingPattern.contains(DAMAGED) ? 0 : 1;
        } else if((group == numbers.size() - 1) && amount == numbers.get(group)){ // Last group
            if(DAMAGED.equals(currentValue)) {
                return 0;
            }
            return remainingPattern.contains(DAMAGED) ? 0 : 1;
        }

        int expectedAmount = numbers.get(group);

        if(OPERATIONAL.equals(currentValue)) {
            if(amount == expectedAmount) {
                result += count(remainingPattern, group+1, 0); // Go to the next group
            } else if(amount == 0){
                result += count(remainingPattern, group, 0); // Skip operational until finding the first #
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

        // Memoization - Save at cache
        memo.put(countStatus, result);

        return result;
    }

}
