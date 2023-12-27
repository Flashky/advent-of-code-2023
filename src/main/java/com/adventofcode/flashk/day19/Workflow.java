package com.adventofcode.flashk.day19;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Workflow extends AbstractWorkflow {

    private List<Rule> rules;

    public Workflow(String input) {
        super(input);

        String rulesString = StringUtils.substringBetween(input, "{", "}");
        String[] rulesArray = rulesString.split(",");
        rules = Arrays.stream(rulesArray).map(Rule::new).collect(Collectors.toList());
    }

    @Override
    public long run(Part part) {
        for(Rule rule : rules) {
            if(rule.matches(part)) {
                // Redirect to workflow
                return workflows.get(rule.getDestinationWorkflow()).run(part);
            }
        }
        return 0;
    }

    @Override
    public long run(Range partsRange) {
        long result = 0;
        Range matchedRange = new Range(partsRange);
        for(Rule rule : rules) {
            if(rule.isBypassRule()) {
                result += workflows.get(rule.getDestinationWorkflow()).run(matchedRange);
            } else {
                Range updatedMatchedRange = rule.matches(matchedRange);
                matchedRange = rule.unmatches(matchedRange);
                result += workflows.get(rule.getDestinationWorkflow()).run(updatedMatchedRange);
            }
        }

        return result;
    }
}
