package com.adventofcode.flashk.day20.jgrapht.modules;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.Pulse;
import com.adventofcode.flashk.day20.jgrapht.PulseEvent;
import org.apache.commons.lang3.StringUtils;

public class Button extends Module {

    public Button() {
        super(StringUtils.EMPTY, "button");
    }

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {
        return Optional.of(Pulse.LOW);
    }
}
