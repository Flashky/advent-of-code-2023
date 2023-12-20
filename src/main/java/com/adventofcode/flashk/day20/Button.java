package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class Button extends SingleOutputModule {

    @Override
    public void process() {
        if(!inputPulses.isEmpty()) {
            Pair<Module, String> inputPulse = this.inputPulses.poll();
            this.output.addPulse(this, inputPulse.getRight());
            this.output.process();
        }

    }
}
