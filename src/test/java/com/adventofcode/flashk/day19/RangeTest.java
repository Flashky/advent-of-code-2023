package com.adventofcode.flashk.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeTest {

    @Test
    void valueAndSizeTest() {
        Range range = new Range();

        Rule rule = new Rule("qkq{x<3:A,crn}");
        Range matchedX = range.matchesRange(rule);

        rule = new Rule("qkq{m<3:A,crn}");
        Range matchedM = matchedX.matchesRange(rule);

        rule = new Rule("qkq{a<3:A,crn}");
        Range matchedA = matchedM.matchesRange(rule);

        rule = new Rule("qkq{s<3:A,crn}");
        Range matchedS = matchedA.matchesRange(rule);

        assertEquals(96L, matchedS.value());
        assertEquals(16, matchedS.size());

    }

    @Test
    void matchesRangeXLessThan() {
        Rule rule = new Rule("qkq{x<1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(999, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeXGreaterThan() {
        Rule rule = new Rule("qkq{x>1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1001, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeMLessThan() {
        Rule rule = new Rule("qkq{m<1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(999, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeMGreaterThan() {
        Rule rule = new Rule("qkq{m>1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1001, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeALessThan() {
        Rule rule = new Rule("qkq{a<1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(999, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeAGreaterThan() {
        Rule rule = new Rule("qkq{a>1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1001, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeSLessThan() {
        Rule rule = new Rule("qkq{s<1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1, matchedRange.getMinS());
        assertEquals(999, matchedRange.getMaxS());
    }

    @Test
    void matchesRangeSGreaterThan() {
        Rule rule = new Rule("qkq{s>1000:A,crn}");
        Range range = new Range();
        Range matchedRange = range.matchesRange(rule);

        assertEquals(1, matchedRange.getMinX());
        assertEquals(4000, matchedRange.getMaxX());

        assertEquals(1, matchedRange.getMinM());
        assertEquals(4000, matchedRange.getMaxM());

        assertEquals(1, matchedRange.getMinA());
        assertEquals(4000, matchedRange.getMaxA());

        assertEquals(1001, matchedRange.getMinS());
        assertEquals(4000, matchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeXLessThan() {

        Rule rule = new Rule("qkq{x<1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1000, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeXGreaterThan() {

        Rule rule = new Rule("qkq{x>1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(1000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeMLessThan() {

        Rule rule = new Rule("qkq{m<1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1000, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeMGreaterThan() {

        Rule rule = new Rule("qkq{m>1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(1000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }


    @Test
    void unmatchedRangeALessThan() {

        Rule rule = new Rule("qkq{a<1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1000, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeAGreaterThan() {

        Rule rule = new Rule("qkq{a>1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(1000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeSLessThan() {

        Rule rule = new Rule("qkq{s<1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1000, unmatchedRange.getMinS());
        assertEquals(4000, unmatchedRange.getMaxS());
    }

    @Test
    void unmatchedRangeSGreaterThan() {

        Rule rule = new Rule("qkq{s>1000:A,crn}");
        Range range = new Range();
        Range unmatchedRange = range.unmatchedRange(rule);

        assertEquals(1, unmatchedRange.getMinX());
        assertEquals(4000, unmatchedRange.getMaxX());

        assertEquals(1, unmatchedRange.getMinM());
        assertEquals(4000, unmatchedRange.getMaxM());

        assertEquals(1, unmatchedRange.getMinA());
        assertEquals(4000, unmatchedRange.getMaxA());

        assertEquals(1, unmatchedRange.getMinS());
        assertEquals(1000, unmatchedRange.getMaxS());
    }
}