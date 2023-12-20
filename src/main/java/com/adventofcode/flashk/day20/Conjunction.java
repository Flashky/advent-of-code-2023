package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class Conjunction extends Module {

    private final Map<Module,String> memory = new HashMap<>();

    public Conjunction(String name) {
        super(name);
    }

    @Override
    public void executePulse() {
        super.executePulse();
        if(!inputPulses.isEmpty()) {
            Pair<Module, String> inputPulse = inputPulses.poll();
            Module module = inputPulse.getLeft();
            String pulse = inputPulse.getRight();

            // Update memory
            memory.put(module, pulse);

            // Verify memory values
            boolean isAllHigh = true;
            for(String memoryValue : memory.values()) {
                if(LOW.equals(memoryValue)) {
                    isAllHigh = false;
                    break;
                }
            }

            String newOutputPulse;
            if(isAllHigh) {
                newOutputPulse = LOW;
            } else {
                newOutputPulse = HIGH;
            }

            for(Module output : outputs) {
                output.addPulse(this, newOutputPulse);
            }

            // Process childs
            /*for(Module output : outputs) {
                output.process();
            }*/


        }
    }
}
