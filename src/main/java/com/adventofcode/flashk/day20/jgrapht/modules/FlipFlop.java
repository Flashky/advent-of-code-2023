package com.adventofcode.flashk.day20.jgrapht.modules;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.Pulse;
import com.adventofcode.flashk.day20.jgrapht.PulseEvent;
import com.adventofcode.flashk.day20.jgrapht.Status;

public class FlipFlop extends Module {

    public FlipFlop(String name) {
        super(name.substring(0,1), name.substring(1));
    }

    private Status status = Status.OFF;

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {

        if (pulseEvent.pulse() == Pulse.LOW) {
            Pulse outputPulse;
            if (status == Status.OFF) {
                status = Status.ON;
                outputPulse = Pulse.HIGH;
            } else  {
                status = Status.OFF;
                outputPulse = Pulse.LOW;
            }
            return Optional.of(outputPulse);

        }

        return Optional.empty();
    }
}
