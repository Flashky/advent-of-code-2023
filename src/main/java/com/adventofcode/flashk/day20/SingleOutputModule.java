package com.adventofcode.flashk.day20;

public abstract class SingleOutputModule extends Module {

    protected Module output;

    @Override
    public void addOutput(Module output) {
        this.output = output;
    }

}
