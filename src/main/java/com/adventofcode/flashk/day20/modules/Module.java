package com.adventofcode.flashk.day20.modules;

import module java.base;
import com.adventofcode.flashk.day20.Pulse;
import com.adventofcode.flashk.day20.PulseEvent;
import lombok.Getter;

@Getter
public abstract class Module {

    private final String prefix;
    private final String name;

    public Module(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    public abstract Optional<Pulse> process(PulseEvent pulseEvent);

    public String getLabel() {
        if(prefix.equals("%")){
            return "f_"+name;
        } else if(prefix.equals("&")) {
            return "c_"+name;
        }
        return prefix+name;
    }
}
