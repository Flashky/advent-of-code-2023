package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class FlipFlop extends Module {

    private static final String ON = "on";
    private static final String OFF = "off";

    private String status = OFF;

    public FlipFlop(String name) {
        super(name);
    }

    @Override
    public void sendPulse(Module origin, String value) {

        System.out.println(origin.getName() + " -"+value+"-> " + getName());

        // Update high and low counters
        if (LOW.equals(value)) {
            this.inputPulses.add(ImmutablePair.of(origin, value));
            increaseLowCount();
        } else {
            increaseHighCount();
        }


    }

    @Override
    public void processPulse() {

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
                    output.sendPulse(this, newOutputPulse);
                }
            }


            for(Module output : outputs) {
                output.processPulse();
            }
        }

    }
}
