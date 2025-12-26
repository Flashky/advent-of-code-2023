package com.adventofcode.flashk.day20.modules;

import module java.base;
import com.adventofcode.flashk.day20.Pulse;
import com.adventofcode.flashk.day20.PulseEvent;
import org.apache.commons.lang3.StringUtils;

public class Button extends Module {

    public static final String NAME = "button";

    public Button() {
        super(StringUtils.EMPTY, NAME);
    }

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {
        return Optional.of(Pulse.LOW);
    }
}
