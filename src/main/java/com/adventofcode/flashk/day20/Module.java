package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.sql.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public abstract class Module {

    public static final String LOW = "low";
    public static final String HIGH = "high";

    protected Deque<Pair<Module,String>> inputPulses = new ArrayDeque<>();

    public void addPulse(Module origin, String value) {
        this.inputPulses.add(ImmutablePair.of(origin, value));
    }

    public abstract void addOutput(Module output);

    public abstract void process();

}
