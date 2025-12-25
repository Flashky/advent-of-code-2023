package com.adventofcode.flashk.day20.jgrapht.modules;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.Pulse;
import com.adventofcode.flashk.day20.jgrapht.PulseEvent;

public class Conjunction extends Module {


    private Map<Module,Pulse> inputMemory = new HashMap<>();

    public Conjunction(String name) {
        super(name.substring(0,1), name.substring(1));
    }

    // TODO inicializar el conjunction al principio del todo
    public void addInputModule(Module inputModule) {
        inputMemory.put(inputModule, Pulse.LOW);
    }

    @Override
    public Optional<Pulse> process(PulseEvent pulseEvent) {

        // Update memory
        inputMemory.put(pulseEvent.origin(), pulseEvent.pulse());

        // If all inputs are high, return a LOW pulse. Otherwise, return a HIGH pulse
        long countHighs = inputMemory.values().stream().filter(p -> p == Pulse.HIGH).count();
        Pulse outputPulse = countHighs == inputMemory.size() ? Pulse.LOW : Pulse.HIGH;
        if(getName().equals("hd") && outputPulse == Pulse.LOW) {
            println(outputPulse);
        }
        return Optional.of(outputPulse);
    }

    public Pulse simulatePulse() {
        long countHighs = inputMemory.values().stream().filter(p -> p == Pulse.HIGH).count();
        return countHighs == inputMemory.size() ? Pulse.LOW : Pulse.HIGH;
    }
}
