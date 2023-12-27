package com.adventofcode.flashk.day20;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PulsePropagation {


    private static final Pattern PREFIX_MODULE_PATTERN = Pattern.compile("([&%])(\\w*)");

    private final Module button;
    private List<Module> modules = new ArrayList<>();

    public PulsePropagation(List<String> inputs) {

        Map<Module, List<String>> tempAdjacencyMap = new HashMap<>();
        Map<String, Module> modulesPerName = new HashMap<>();

        for(String input : inputs) {

            Module module = null;

            if(input.startsWith("broadcaster")) {
                // Si es broadcaster, crear broadcaster y añadir sus nombres adyacentes.
                module = new Broadcaster();

                String adjacents = StringUtils.deleteWhitespace(input.substring(15, input.length()));
                String[] adjacentsOutputs = adjacents.split(",");

                tempAdjacencyMap.put(module, Arrays.asList(adjacentsOutputs));
                modulesPerName.put(module.getName(), module);

            } else  {
                Matcher matcher = PREFIX_MODULE_PATTERN.matcher(input);
                if(matcher.find()) {
                    String type = matcher.group(1);
                    String name = matcher.group(2);

                    if("%".equals(type)) {
                        module = new FlipFlop(name);
                    } else {
                        module = new Conjunction(name);
                    }

                    String adjacents = StringUtils.deleteWhitespace(input.substring(input.indexOf(">")+1, input.length()));
                    String[] adjacentsOutputs = adjacents.split(",");

                    tempAdjacencyMap.put(module, Arrays.asList(adjacentsOutputs));
                    modulesPerName.put(module.getName(), module);
                }

            }

            if(module != null) {
                modules.add(module);
            }


        }

        // Link graph nodes
        Set<Module>  moduleKeys = tempAdjacencyMap.keySet();
        for(Module module : moduleKeys) {
            List<String> outputModuleNames = tempAdjacencyMap.get(module);
            for(String outputModuleName : outputModuleNames) {
                if(modulesPerName.containsKey(outputModuleName)) {
                    module.addOutput(modulesPerName.get(outputModuleName));
                } else {
                    Module output = new Output(outputModuleName);
                    modules.add(output);
                    module.addOutput(output);
                }
            }
        }

        button = new Button("button");
        button.addOutput(modulesPerName.get("broadcaster"));
    }

    public long solveA(int pressTimes) {

        for(int i = 0; i < pressTimes; i++) {
            button.sendPulse(button, Module.LOW);
            button.processPulse();
        }

        long lows = 0;
        long highs = 0;
        for(Module module : modules) {
            lows += module.getLowCount();
            highs += module.getHighCount();
        }

        return lows * highs;
    }

}
