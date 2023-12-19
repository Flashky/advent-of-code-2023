package com.adventofcode.flashk.day19;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Aplenty {


    private Map<String, Workflow> workflows = new HashMap<>();

    private Map<Part,String> workflowPerPart = new LinkedHashMap<>();

    public Aplenty(List<String> inputs) {

        boolean readRule = true;
        Iterator<String> inputsIterator = inputs.iterator();
        while(inputsIterator.hasNext()) {
            String input = inputsIterator.next();
            if(StringUtils.isBlank(input)) {
                readRule = false;
            } else if (readRule) {
                Workflow workflow = new Workflow(input);
                workflows.put(workflow.getName(), workflow);
            } else {
                Part part = new Part(input);
                workflowPerPart.put(part, StringUtils.EMPTY);
            }
        }
        int a = 3;
    }

    public long solveA() {

        Set<Part> parts = workflowPerPart.keySet();
        for(Part part : parts) {
            String finalWorklow = executeWorkflows(part);
            workflowPerPart.put(part, finalWorklow);
        }

        return countAcceptedParts();
    }

    public long solveB() {
        long result = 0;
        for(int x = 1; x <= 4000; x++) {
            for(int m = 1; m <= 4000; m++) {
                for(int a = 1; a <= 4000; a++) {
                    for(int s = 1; s <= 4000; s++) {
                        Part part = new Part(x,m,a,s);
                        String finalWorkflow = executeWorkflows(part);
                        if(finalWorkflow.equals("A")) {
                            result += part.value();
                            //System.out.println("Part: "+part + " - Workflow: "+finalWorkflow);
                        }
                    }
                }
            }
        }

        return result;
    }

    public long countAcceptedParts() {
        long result = 0;
        Set<Part> parts = workflowPerPart.keySet();
        for(Part part : parts) {
            if(workflowPerPart.get(part).equals("A")) {
                result += part.value();
            }
        }
        return result;
    }
    private String executeWorkflows(Part part) {
        Workflow workflow = workflows.get("in");
        String destinationWorkflow;
        do {
            destinationWorkflow = workflow.run(part);
            workflow = workflows.get(destinationWorkflow);
        } while(workflow != null);

        return destinationWorkflow;
    }
}
