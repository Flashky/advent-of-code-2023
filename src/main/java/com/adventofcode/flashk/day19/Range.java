package com.adventofcode.flashk.day19;

import lombok.Getter;

@Getter
public class Range {

    private static final int DEFAULT_MIN = 1;
    private static final int DEFAULT_MAX = 4000;

    private int minX = DEFAULT_MIN;
    private int maxX = DEFAULT_MAX;
    private int minM = DEFAULT_MIN;
    private int maxM = DEFAULT_MAX;
    private int minA = DEFAULT_MIN;
    private int maxA = DEFAULT_MAX;
    private int minS = DEFAULT_MIN;
    private int maxS = DEFAULT_MAX;

    public Range() {
    }

    public Range(Range other) {
        minX = other.minX;
        maxX = other.maxX;
        minM = other.minM;
        maxM = other.maxM;
        minA = other.minA;
        maxA = other.maxA;
        minS = other.minS;
        maxS = other.maxS;
    }

    public long size() {
        long sizeX = maxX - minX + 1L;
        long sizeM = maxM - minM + 1L;
        long sizeA = maxA - minA + 1L;
        long sizeS = maxS - minS + 1L;

        return sizeX * sizeM * sizeA * sizeS;
    }

    /**
     * Creates a new range that matches the rule passed as a parameter.
     * @param rule the rule to check
     * @return a new range that matches the rule
     */
    public Range matchesRange(Rule rule) {

        if(rule.isBypassRule()) {
            return this;
        }

        return switch (rule.getLetter()) {
            case 'x' -> splitByXMatch(rule);
            case 'm' -> splitByMMatch(rule);
            case 'a' -> splitByAMatch(rule);
            case 's' -> splitBySMatch(rule);
            default -> throw new IllegalArgumentException("Invalid letter: '"+rule.getLetter()+"'");
        };
    }

    /**
     * Creates a new range that does not match the rule passed as a parameter.
     * @param rule the rule to check
     * @return a new range that does not match the rule
     */
    public Range unmatchedRange(Rule rule) {

        if(rule.isBypassRule()) {
            return this;
        }
        
        return switch (rule.getLetter()) {
            case 'x' -> splitByXNoMatch(rule);
            case 'm' -> splitByMNoMatch(rule);
            case 'a' -> splitByANoMatch(rule);
            case 's' -> splitBySNoMatch(rule);
            default -> throw new IllegalArgumentException("Invalid letter: '"+rule.getLetter()+"'");
        };
    }

    private Range splitByXMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.maxX = rule.getValue()-1;
        }  else {
            splitRange.minX = rule.getValue()+1;
        }
        return splitRange;
    }

    private Range splitByMMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.maxM = rule.getValue()-1;
        }  else {
            splitRange.minM = rule.getValue()+1;
        }
        return splitRange;
    }

    private Range splitByAMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.maxA = rule.getValue()-1;
        }  else {
            splitRange.minA = rule.getValue()+1;
        }
        return splitRange;
    }

    private Range splitBySMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.maxS = rule.getValue()-1;
        }  else {
            splitRange.minS = rule.getValue()+1;
        }
        return splitRange;
    }

    private Range splitByXNoMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.minX = rule.getValue();
        }  else {
            splitRange.maxX = rule.getValue();
        }
        return splitRange;
    }

    private Range splitByMNoMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.minM = rule.getValue();
        }  else {
            splitRange.maxM = rule.getValue();
        }
        return splitRange;
    }

    private Range splitByANoMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.minA = rule.getValue();
        }  else {
            splitRange.maxA = rule.getValue();
        }
        return splitRange;
    }

    private Range splitBySNoMatch(Rule rule) {

        Range splitRange = new Range(this);
        if(rule.getCondition() == Rule.LESS_THAN) {
            splitRange.minS = rule.getValue();
        }  else {
            splitRange.maxS = rule.getValue();
        }
        return splitRange;
    }
}
