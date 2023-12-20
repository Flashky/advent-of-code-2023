package com.adventofcode.flashk.day20;

import java.util.ArrayList;
import java.util.List;

public abstract class MultiOutputModule extends Module {

    protected List<Module> outputs = new ArrayList<>();

    @Override
    public void addOutput(Module output) {
        this.outputs.add(output);
    }

}
