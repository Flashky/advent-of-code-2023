package com.adventofcode.flashk.day19;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Workflow {

    private static final Pattern NAME_PATTERN = Pattern.compile("(\\w*)\\{");

    @Getter
    private String name;
    private List<Rule> rules;

    public Workflow(String input) {
        Matcher matcher = NAME_PATTERN.matcher(input);
        if(matcher.find()) {
            name = matcher.group(1);
        }

        String rulesString = StringUtils.substringBetween(input, "{", "}");
        String[] rulesArray = rulesString.split(",");
        rules = Arrays.stream(rulesArray).map(Rule::new).collect(Collectors.toList());

        // TODO minor improvement
        // some workflows lead always to the same output workflow, in that case
        // rules can be directely bypassed.
        boolean sameWorkflows = true;
        String workflowName = rules.get(0).getDestinationWorkflow();
        for(Rule rule : rules) {
            if(!rule.getDestinationWorkflow().equals(workflowName)) {
                sameWorkflows = false;
            }
        }

        if(sameWorkflows) {
            rules.clear();
            rules.add(new Rule(workflowName));
        }
    }

    public String run(Part part) {
        for(Rule rule : rules) {
            if(rule.matches(part)) {
                return rule.getDestinationWorkflow();
            }
        }
        return "UNKNOWN";
    }
}
