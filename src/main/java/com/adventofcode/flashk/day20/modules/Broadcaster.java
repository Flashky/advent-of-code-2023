package com.adventofcode.flashk.day20.modules;

import module java.base;
import com.adventofcode.flashk.day20.Pulse;
import com.adventofcode.flashk.day20.PulseEvent;
import org.apache.commons.lang3.StringUtils;

public class Broadcaster extends Module {

    public static final String NAME = "broadcaster";

    public Broadcaster() {
        super(StringUtils.EMPTY, NAME);
    }

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {
        return Optional.of(pulseEvent.pulse());
    }
}
