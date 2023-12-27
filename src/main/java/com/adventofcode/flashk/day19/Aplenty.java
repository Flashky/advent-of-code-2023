package com.adventofcode.flashk.day19;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Aplenty {

    private AbstractWorkflow start;
    private final List<Part> parts = new ArrayList<>();

    public Aplenty(List<String> inputs) {

        AbstractWorkflow.clearWorkflows();

        // Default workflows
        new WorkflowAccepted();
        new WorkflowRejected();

        boolean readRule = true;
        Iterator<String> inputsIterator = inputs.iterator();
        while(inputsIterator.hasNext()) {
            String input = inputsIterator.next();
            if(StringUtils.isBlank(input)) {
                readRule = false;
            } else if (readRule) {
                AbstractWorkflow workflow = new Workflow(input);
                if(workflow.getName().equals("in")) {
                    start = workflow;
                }
            } else {
                parts.add(new Part(input));
            }
        }


    }

    public long solveA() {

        long result = 0;
        for(Part part : parts) {
            result += start.run(part);
        }

        return result;
    }

    public long solveB() {
        return start.run(new Range());
    }
}
