package com.adventofcode.flashk.day20.jgrapht;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.modules.Module;

public record PulseEvent(Pulse pulse, Module origin, Module destination) {
}
