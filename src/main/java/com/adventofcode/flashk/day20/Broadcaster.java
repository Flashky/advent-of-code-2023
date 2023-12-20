package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.Pair;

public class Broadcaster extends Module {

    public Broadcaster() {
        super("broadcaster");
    }

    @Override
    public void executePulse() {
        super.executePulse();
        if(!inputPulses.isEmpty()) {

            Pair<Module, String> inputPulse = this.inputPulses.poll();
            String newOutputPulse = inputPulse.getRight();

            // Processes the pulse:
            // A broadcast module just sends the pulse to other modules.
            for(Module output : outputs) {
                output.addPulse(this, newOutputPulse);
            }

            // Recursively process the pulses
            /*for(Module output : outputs) {
                output.process();
            }*/
        }


    }
}
