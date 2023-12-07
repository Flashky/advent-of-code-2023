package com.adventofcode.flashk.day07;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CamelCards {

    private final List<String> inputs;

    public CamelCards(List<String> inputs) {
        this.inputs = inputs;
    }

    public int solve(boolean useJokers) {

        AtomicInteger rank = new AtomicInteger(1);
        return inputs.stream()
                .map(s -> new Hand(s, useJokers))
                .sorted()
                .map(h -> h.getValue(rank.getAndIncrement()))
                .reduce(0, Integer::sum);

    }
}
