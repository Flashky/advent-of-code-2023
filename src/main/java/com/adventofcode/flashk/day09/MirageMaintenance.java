package com.adventofcode.flashk.day09;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class MirageMaintenance {

    private final List<String> inputs;

    public MirageMaintenance(List<String> inputs) {
        this.inputs = inputs;
    }

    public long solve(boolean isLeft) {
        long result = 0;
        for(String input : inputs) {

            // Parse an input line
            String[] values = input.split(StringUtils.SPACE);
            List<Long> historyValues = Arrays.stream(values).map(Long::parseLong).toList();

            // Generate and extrapolate
            Deque<Sequence> sequences = generateSequences(historyValues);
            long extrapolation = extrapolate(sequences, isLeft);

            result += extrapolation;

        }
        return result;
    }

    private Deque<Sequence> generateSequences(List<Long> historyValues) {

        Sequence sequence;
        Deque<Sequence> sequences = new ArrayDeque<>();
        sequences.add(new Sequence(historyValues));

        do {
            sequence = new Sequence(sequences.peekLast().getValues());
            sequences.add(sequence);
        } while(!sequence.isZero());

        return sequences;
    }


    private long extrapolate(Deque<Sequence> sequences, boolean isLeft) {

        long nextValue = 0;

        while(!sequences.isEmpty()) {
            Sequence sequence = sequences.pollLast();
            if(isLeft) {
                nextValue = sequence.extrapolateLeft(nextValue);
            } else {
                nextValue = sequence.extrapolateRight(nextValue);
            }
        }
        
        return nextValue;
    }

}
