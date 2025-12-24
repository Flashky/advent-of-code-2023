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

    private static final String UNKNOWN_PATTERN = "[?]";

    private final String row;
    private final List<Integer> numbers;
    private final int expectedTotalDamaged;
    private final String expectedPattern;
    //private List<Integer> unknownIndexes = new ArrayList<>();

    public SpringRecord(String input, boolean unFold) {
        String[] inputParts = input.split(StringUtils.SPACE);
        String rowRecord = inputParts[0];
        String numericPartStr = inputParts[1];

        if(unFold) {
            rowRecord = StringUtils.repeat(rowRecord+ UNKNOWN, REPETITIONS);
            rowRecord = StringUtils.chop(rowRecord); //rowRecord.substring(0, rowRecord.length()-1); // remove last "?"
            // StringUtils.chop(rowRecord);
            numericPartStr = StringUtils.repeat(numericPartStr+",", REPETITIONS);
            StringUtils.chop(numericPartStr);
            //numericPartStr = numericPartStr.substring(0, numericPartStr.length()-1);

        }

        row = rowRecord;
        String[] numericPart = numericPartStr.split(",");
        numbers = Arrays.stream(numericPart).map(Integer::valueOf).toList();

        // Obtain expected pattern
        StringBuilder sb = new StringBuilder();
        for(int number : numbers) {
            sb.append(StringUtils.repeat(DAMAGED, number)).append(OPERATIONAL);
        }

        expectedPattern = sb.substring(0, sb.toString().length()-1);

        // Obtain unkown indexes
        /*
        char[] rowArray = row.toCharArray();
        for(int i = 0; i < rowArray.length; i++) {
            if(rowArray[i] == UNKNOWN.charAt(0)) {
                unknownIndexes.add(i);
            }
        }*/

        expectedTotalDamaged = numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public long countArrangements2() {
        Queue<Integer> numbersToCheck = new ArrayDeque<>(numbers);

        String pattern = row;
        while(!pattern.isBlank()) {

        }
        return 0;
    }
    public long countArrangements() {
        /*
        Queue<Integer> numbersToCheck = new ArrayDeque<>(numbers);

        // TODO podría plantearse eliminar los trailing '.' de la cadena, no aportan nada.

        long result = 0;

        int firstExpectedNumber = numbersToCheck.poll();

        if(row.startsWith(UNKNOWN)) {
            String damagedPattern = row.replaceFirst(UNKNOWN_PATTERN, DAMAGED);
            String emptyPattern = row.replaceFirst(UNKNOWN_PATTERN, OPERATIONAL);

            result += count(damagedPattern, firstExpectedNumber, numbersToCheck);
            result += count(emptyPattern, firstExpectedNumber, numbersToCheck);

        } else {
            result = count(row, firstExpectedNumber, numbersToCheck);
        }
*/
        long result = count(row, 0, 0);
        return result;
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

        if(group >= numbers.size()) {
            return (pattern.contains(DAMAGED)) ? 0 : 1;
        }

        String currentValue = pattern.substring(0,1);
        String remainingPattern = pattern.substring(1);
        int expectedAmount = numbers.get(group);

        if(OPERATIONAL.equals(currentValue)) {
            if(amount == expectedAmount) {
                result += count(remainingPattern, group+1, 0); // Go to the next group
            } else {
                result += count(remainingPattern, group, 0); // Start counting again
            }
        } else if(DAMAGED.equals(currentValue)){

            if(amount > expectedAmount) {
                result += count(remainingPattern, group, 0); // Start counting again
            } else {
                result += count(remainingPattern, group, amount+1); // Continue counting
            }

        } else { // UNKNOWN
            result += count(DAMAGED+remainingPattern, group, amount);
            result += count(OPERATIONAL+remainingPattern, group, amount);
        }

        return result;
    }


    private long count(String pattern, int expectedDamagedCount, Queue<Integer> numbersToCheck) {

        // Remove leading '.' from String
        String strippedPattern = StringUtils.stripStart(pattern, OPERATIONAL);

        // Expected damaged count must fit in the remaining pattern string
        if(strippedPattern.length() < expectedDamagedCount) {
            return 0;
        }

        char[] patternChars = strippedPattern.toCharArray();

        // The following expectedDamagedCount positions must be either damaged or unknown
        // If that is not the case, there is no solution for this pattern.
        for(int i = 0; i < expectedDamagedCount; i++) {
            if(patternChars[i] == OPERATIONAL.charAt(0)) {
                return 0;
            }
        }

        // Check if the pattern is followed by a damaged character (invalid) or not (valid)
        String remainingString = strippedPattern.substring(expectedDamagedCount);

        if(remainingString.startsWith(DAMAGED)) {
            return 0; // Invalid pattern
        } else if(remainingString.length() > 1){
            remainingString = remainingString.substring(1); // Skip one
        } else {
            return 1; // Remaining string is empty
        }

        // TODO  hasta aquí el código tiene buena pinta SEGURO

        // After the pattern, there are different situations:
        // - CB 1: No more pattern to analyze. The pattern is correct. result = 1
        // - CB 2: There are no damaged springs at the remaining pattern. The pattern is correct. result = 1
        // - CB 3: There is at least one or more damaged springs at the remainaing pattern. The pattern is not valid. result = 0
        if(numbersToCheck.isEmpty()) {
            return isTrailingValid(remainingString) ? 1 : 0;
        }

        // TODO  hasta aquí el código tiene buena pinta DUDOSO

        // - Case B - There are more numbers to handle
        // - If the next one is damaged, solution is not valid.
        // - If the next one is unknown, just handle it as it is operational.
        // - If the next one is operational
        // - If there is no more pattern.

        remainingString = strippedPattern.substring(expectedDamagedCount);

        // Invalid, a damaged spring cannot come after a full group
        if(remainingString.startsWith(DAMAGED)) {
            return 0;
        } else if(remainingString.length() > 1){
            remainingString = remainingString.substring(1); // Skip one
        }

        // Recursive cases
        long count = 0;
        int nextExpectedDamagedCount = numbersToCheck.poll();

        if(remainingString.startsWith(UNKNOWN)) {

            String damagedPattern = remainingString.replaceFirst(UNKNOWN_PATTERN, DAMAGED);
            String emptyPattern = remainingString.replaceFirst(UNKNOWN_PATTERN, OPERATIONAL);

            count += count(damagedPattern, nextExpectedDamagedCount, numbersToCheck);
            count += count(emptyPattern, nextExpectedDamagedCount, numbersToCheck);

        } else { // OPERATIONAL
            count += count(remainingString, nextExpectedDamagedCount, numbersToCheck);
        }

        // Backtrack
        numbersToCheck.add(nextExpectedDamagedCount);

        // TODO Este sería un buen lugar para aplicar la memoization en el mapa

        return count;

    }

    private boolean isTrailingValid(String remainingString) {
        return !remainingString.contains(String.valueOf(DAMAGED));
    }


}
