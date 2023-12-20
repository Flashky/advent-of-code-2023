package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.Pair;

public class FlipFlop extends Module {

    private static final String ON = "on";
    private static final String OFF = "off";

    private String status = OFF;

    public FlipFlop(String name) {
        super(name);
    }

    @Override
    public void executePulse() {
        super.executePulse();
        if(!inputPulses.isEmpty())
        {
            Pair<Module, String> nextPulse = this.inputPulses.poll();
            String pulse = nextPulse.getRight();

            if (LOW.equals(pulse))
            {
                String newOutputPulse;
                if (OFF.equals(status)) {
                    status = ON;
                    newOutputPulse = HIGH;
                } else  {
                    status = OFF;
                    newOutputPulse = LOW;
                }

                for(Module output : outputs) {
                    output.addPulse(this, newOutputPulse);
                }
            }


            /*for(Module output : outputs) {
                output.process();
            }*/
        }

    }
}
