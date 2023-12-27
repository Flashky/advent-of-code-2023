package com.adventofcode.flashk.day19;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public abstract class AbstractWorkflow {

    private static final Pattern NAME_PATTERN = Pattern.compile("(\\w*)\\{");

    protected static final Map<String,AbstractWorkflow> workflows = new HashMap<>();

    private final String name;

    protected AbstractWorkflow(String input) {

        Matcher matcher = NAME_PATTERN.matcher(input);
        if(matcher.find()) {
            name = matcher.group(1);
        } else {
            name = input;
        }

        workflows.putIfAbsent(name, this);
    }

    public abstract long run(Part part);
    public abstract long run(Range partsRange);

    public static void clearWorkflows() {
        workflows.clear();
    }
}
