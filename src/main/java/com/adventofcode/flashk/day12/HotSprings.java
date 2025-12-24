package com.adventofcode.flashk.day12;

import module java.base;

public class HotSprings {

    private final List<ConditionRecord> conditionRecords;

    public HotSprings(List<String> inputs, boolean unfold) {
        conditionRecords = inputs.stream().map(input -> new ConditionRecord(input, unfold)).toList();
    }

    public long solve() {
        return conditionRecords.stream().mapToLong(ConditionRecord::count).sum();
    }

}
