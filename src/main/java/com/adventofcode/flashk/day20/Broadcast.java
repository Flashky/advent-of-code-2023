package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.Pair;

public class Broadcast extends MultiOutputModule{

    @Override
    public void process() {
        if(!this.inputPulses.isEmpty()) {
            Pair<Module, String> inputPulse = this.inputPulses.poll();

            // Send a pulse
            for(Module output : outputs) {
                output.addPulse(this, inputPulse.getRight());
            }

            // Process pulse in order of sending
            for(Module output : outputs) {
                output.process();
            }
        }


    }
}
