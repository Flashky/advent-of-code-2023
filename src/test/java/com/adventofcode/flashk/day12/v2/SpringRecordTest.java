package com.adventofcode.flashk.day12.v2;

import static org.junit.jupiter.api.Assertions.*;

import module java.base;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


class SpringRecordTest {

    @ParameterizedTest
    @MethodSource("provideFoldedEmpty")
    void foldedEmptyTest(String pattern) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(0, springRecord.countArrangements());
    }

    @ParameterizedTest
    @MethodSource("providedFoldedExamples")
    void foldedExamplesTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.countArrangements());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedConsecutiveUnknownUnique")
    void foldedConsecutiveUnknownTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.countArrangements());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedConsecutiveDamagedFill")
    @MethodSource("provideFoldedConsecutiveUnknownFill")
    void foldedConsecutiveFillTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.countArrangements());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedAlternatingUnknownStart")
    void foldedAlternatingUnkownStartTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.countArrangements());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedAlternatingOperationalStart")
    void foldedAlternatingOperationalStartTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.countArrangements());
    }

    private static Stream<String> provideFoldedEmpty() {
        return Stream.of(   ". 1", ".. 1", "... 1", ".... 1", "..... 1" , "...... 1");
    }

    private static Stream<Arguments> provideFoldedAlternatingUnknownStart() {
        return Stream.of(
                Arguments.of("?. 1", 1),
                Arguments.of("?.? 1", 2),
                Arguments.of("?.?.? 1", 3),
                Arguments.of("?.?.?.? 1", 4)
        );
    }

    private static Stream<Arguments> provideFoldedAlternatingOperationalStart() {
        return Stream.of(
                Arguments.of(".? 1", 1),
                Arguments.of(".?.? 1", 2),
                Arguments.of(".?.?.? 1", 3),
                Arguments.of(".?.?.?.? 1", 4)
        );
    }

    private static Stream<Arguments> provideFoldedConsecutiveUnknownUnique() {
        return Stream.of(
                Arguments.of("? 1", 1),
                Arguments.of("?? 1", 2),
                Arguments.of("??? 1", 3),
                Arguments.of("???? 1", 4),
                Arguments.of("????? 1", 5),
                Arguments.of("?????? 1", 6)
        );
    }

    private static Stream<Arguments> provideFoldedConsecutiveUnknownFill() {
        return Stream.of(
                Arguments.of("? 1", 1),
                Arguments.of("?? 2", 1),
                Arguments.of("??? 3", 1),
                Arguments.of("???? 4", 1),
                Arguments.of("????? 5", 1),
                Arguments.of("?????? 6", 1),
                Arguments.of("..??????.. 6", 1)
        );
    }

    private static Stream<Arguments> provideFoldedConsecutiveDamagedFill() {
        return Stream.of(
                Arguments.of("# 1", 1),
                Arguments.of("## 2", 1),
                Arguments.of("### 3", 1),
                Arguments.of("#### 4", 1),
                Arguments.of("##### 5", 1),
                Arguments.of("###### 6", 1),
                Arguments.of("..######.. 6", 1)
        );
    }

    private static Stream<Arguments> providedFoldedExamples() {
        return Stream.of(
                Arguments.of("???.### 1,1,3", 1),
                Arguments.of(".??..??...?##. 1,1,3", 4),
                Arguments.of("?#?#?#?#?#?#?#? 1,3,1,6", 1),
                Arguments.of("????.#...#... 4,1,1", 1),
                Arguments.of("????.######..#####. 1,6,5", 4),
                Arguments.of("?###???????? 3,2,1", 10)
        );
    }

}