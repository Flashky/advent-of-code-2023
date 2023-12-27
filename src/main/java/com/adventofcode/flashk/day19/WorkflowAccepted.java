package com.adventofcode.flashk.day19;

public class WorkflowAccepted extends AbstractWorkflow {

    public WorkflowAccepted() {
        super("A");
    }

    @Override
    public long run(Part part) {
        return part.value();
    }

    @Override
    public long run(Range partsRange) {
        return partsRange.size();
    }
}
