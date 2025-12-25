package com.adventofcode.flashk.day20.jgrapht.modules;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.Pulse;
import com.adventofcode.flashk.day20.jgrapht.PulseEvent;
import com.adventofcode.flashk.day20.jgrapht.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
public abstract class Module {

    private final String prefix;
    private final String name;


    public Module(String prefix, String name) {
        this.prefix = prefix;
        this.name = name;
    }

    @Setter
    private boolean isVisited = false;

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
