package com.adventofcode.flashk.day20;

import module java.base;
import com.adventofcode.flashk.day20.modules.Module;

public record PulseEvent(Pulse pulse, Module origin, Module destination) {
}
