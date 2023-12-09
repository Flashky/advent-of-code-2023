package com.adventofcode.flashk.day09;

import lombok.Getter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

@Getter
public class Sequence {

    private Deque<Long> values = new ArrayDeque<>();
    private boolean isZero = true;

    public Sequence(List<Long> historyValues) {
        values = new ArrayDeque<>(historyValues);
        isZero = false;
    }

    public Sequence(Deque<Long> sequenceValues) {
        Iterator<Long> historyValuesIterator = sequenceValues.iterator();
        long previousValue = historyValuesIterator.next();
        while (historyValuesIterator.hasNext()) {
            long currentValue = historyValuesIterator.next();
            long step = currentValue - previousValue;
            values.add(step);
            isZero &= (step == 0);
            previousValue = currentValue;
        }
    }

    public long extrapolateRight(long bottomValue) {
        values.add(values.peekLast() + bottomValue);
        return values.peekLast();
    }

    public long extrapolateLeft(long bottomValue) {
        values.addFirst(values.peekFirst() - bottomValue);
        return values.peekFirst();
    }

}
