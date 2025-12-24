package com.adventofcode.flashk.day12.v2;

import static org.junit.jupiter.api.Assertions.*;

import module java.base;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@TestMethodOrder(OrderAnnotation.class)
class SpringRecordTest {

    @ParameterizedTest
    @MethodSource("provideFoldedEmpty")
    @Order(0)
    void foldedEmptyTest(String pattern) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(0, springRecord.count());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedConsecutiveUnknownUnique")
    @Order(1)
    void foldedConsecutiveUnknownTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.count());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedConsecutiveDamagedFill")
    @MethodSource("provideFoldedConsecutiveUnknownFill")
    @Order(2)
    void foldedConsecutiveFillTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.count());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedAlternatingUnknownStart")
    @Order(3)
    void foldedAlternatingUnkownStartTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.count());
    }

    @ParameterizedTest
    @MethodSource("provideFoldedAlternatingOperationalStart")
    @Order(4)
    void foldedAlternatingOperationalStartTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.count());
    }

    @ParameterizedTest
    @MethodSource("providedUnfoldedExamples")
    @Order(5)
    void unfoldedExamplesTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, true);
        assertEquals(expected, springRecord.count());
    }

    @ParameterizedTest
    @MethodSource("providedFoldedExamples")
    @Order(6)
    void foldedExamplesTest(String pattern, long expected) {
        SpringRecord springRecord = new SpringRecord(pattern, false);
        assertEquals(expected, springRecord.count());
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

    private static Stream<Arguments> providedUnfoldedExamples() {
        return Stream.of(
                Arguments.of(".# 1", 1),
                Arguments.of("???.### 1,1,3", 1),
                Arguments.of(".??..??...?##. 1,1,3", 16384),
                Arguments.of("?#?#?#?#?#?#?#? 1,3,1,6", 1),
                Arguments.of("????.#...#... 4,1,1", 16),
                Arguments.of("????.######..#####. 1,6,5", 2500),
                Arguments.of("?###???????? 3,2,1", 506250)
        );
    }

}