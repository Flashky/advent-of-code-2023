package com.adventofcode.flashk.day12.v2;

import static java.lang.IO.println;

import module java.base;

public class HotSpringsRefactor {

    private final List<SpringRecord> springRecords;

    public HotSpringsRefactor(List<String> inputs, boolean unfold) {
        springRecords = inputs.stream().map(input -> new SpringRecord(input, unfold)).toList();
    }

    public long solve() {
        long result = springRecords.stream().map(SpringRecord::countArrangements).reduce(0L, Long::sum);
        return result;
    }

}
