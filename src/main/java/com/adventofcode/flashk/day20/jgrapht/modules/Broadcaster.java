package com.adventofcode.flashk.day20.jgrapht.modules;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.Pulse;
import com.adventofcode.flashk.day20.jgrapht.PulseEvent;
import org.apache.commons.lang3.StringUtils;

public class Broadcaster extends Module {

    public Broadcaster() {
        super(StringUtils.EMPTY, "broadcaster");
    }

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {
        return Optional.of(pulseEvent.pulse());
    }
}
