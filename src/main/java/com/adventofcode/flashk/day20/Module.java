package com.adventofcode.flashk.day20;

import lombok.Getter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class Module {

    public static final String LOW = "low";
    public static final String HIGH = "high";

    @Getter
    private final String name;

    protected Deque<Pair<Module,String>> inputPulses = new ArrayDeque<>();

    @Getter
    protected List<Module> outputs = new ArrayList<>();

    @Getter
    private long lowCount;

    @Getter
    private long highCount;

    protected Module(String name) {
        this.name = name;
    }

    public void sendPulse(Module origin, String value) {
        this.inputPulses.add(ImmutablePair.of(origin, value));
        //System.out.println(origin.name + " -"+value+"-> " + name);

        // Update high and low counters
        if(LOW.equals(value)) {
            increaseLowCount();
        } else {
            increaseHighCount();
        }

    }

    public void increaseLowCount() {
        lowCount++;
    }

    public void increaseHighCount() {
        highCount++;
    }

    public void addOutput(Module output) {
        this.outputs.add(output);
    }

    public abstract void processPulse();

}
