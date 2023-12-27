package com.adventofcode.flashk.day19;

public class WorkflowRejected extends AbstractWorkflow {

    public WorkflowRejected() {
        super("R");
    }

    @Override
    public long run(Part part) {
        return 0;
    }

    @Override
    public long run(Range partsRange) {
        return 0;
    }
}
