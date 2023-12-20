package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.Pair;

public class Button extends Module {

    public Button(String name) {
        super(name);
    }

    @Override
    public void processPulse() {
        if(!inputPulses.isEmpty()) {
            Pair<Module, String> inputPulse = this.inputPulses.poll();
            String newOutputPulse = inputPulse.getRight();

            outputs.get(0).sendPulse(this, newOutputPulse);
            outputs.get(0).processPulse();

        }

    }
}
