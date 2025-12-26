package com.adventofcode.flashk.day20.modules;

import module java.base;
import com.adventofcode.flashk.day20.Pulse;
import com.adventofcode.flashk.day20.PulseEvent;
import org.apache.commons.lang3.StringUtils;

/// Output is an special module that is only present on the second example
/// The input doesn't have an output module
public class Output extends Module {

    public Output(String name) {
        super(StringUtils.EMPTY, name);
    }

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {
        return Optional.empty();
    }
}
