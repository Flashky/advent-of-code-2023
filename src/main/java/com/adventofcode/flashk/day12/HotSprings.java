package com.adventofcode.flashk.day12;

import java.util.List;


public class HotSprings {

    private List<HotSpringRecord> records;
    public HotSprings(List<String> inputs, boolean unFold) {
        records = inputs.stream().map(input -> new HotSpringRecord(input, unFold)).toList();
    }

    public long solveA() {
        return records.stream().map(HotSpringRecord::countArrangements).reduce(0L, Long::sum);
    }
}
