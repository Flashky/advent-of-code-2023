package com.adventofcode.flashk.day20;

import module java.base;
import com.adventofcode.flashk.day20.modules.Broadcaster;
import com.adventofcode.flashk.day20.modules.Button;
import com.adventofcode.flashk.day20.modules.Conjunction;
import com.adventofcode.flashk.day20.modules.FlipFlop;
import com.adventofcode.flashk.day20.modules.Module;
import com.adventofcode.flashk.day20.modules.Output;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import static java.util.function.Predicate.not;

public class PulsePropagation {

    private static final String WHITESPACES_REGEX = "\\s";

    @Getter
    private final Map<Module, List<Module>> graph = new LinkedHashMap<>();

    // Auxiliar structures
    private final Map<String, Module> modulesByName = new HashMap<>();

    // Solution attributes
    private long lowPulses = 0;
    private long highPulses = 0;

    public PulsePropagation(List<String> inputs) {

       // Manually add the button to broadcaster definition to the input
       inputs.addFirst("button -> broadcaster");

       // Initialize graphs
       Map<Module,String[]> outputsByModule = createVertices(inputs);
       createEdges(outputsByModule);
    }

    private Map<Module, String[]> createVertices(List<String> inputs) {

        Map<Module,String[]> outputsByModule = new HashMap<>();

        // Create all the vertices
        for(String input : inputs) {
            String[] splitInput = input.split(" -> ");
            String moduleName = splitInput[0];
            String[] outputs = splitInput[1].replaceAll(WHITESPACES_REGEX, StringUtils.EMPTY).split(",");

            // Create the vertex module
            Module module = createModule(moduleName);

            // Add module to dictionary maps
            modulesByName.put(module.getName(), module);
            outputsByModule.put(module, outputs);

            // Add vertex module to the graph
            graph.put(module, new ArrayList<>());

        }

        return outputsByModule;
    }

    private void createEdges(Map<Module, String[]> outputsByModule) {
        for(Module module : outputsByModule.keySet()) {
            String[] outputs = outputsByModule.get(module);
            for(String name : outputs){

                Module outputModule;
                if(modulesByName.containsKey((name))) {
                    outputModule = modulesByName.get(name);
                } else {
                    // This is an output module that is only used as sink
                    outputModule = new Output(name);
                    graph.put(outputModule, Collections.emptyList());
                }

                // Add edges to the graph
                graph.get(module).add(outputModule);

                // If it is a conjunction module, add the input to its memory
                if(outputModule instanceof Conjunction) {
                    ((Conjunction) outputModule).addInputModule(module);
                }
            }
        }
    }

    private Module createModule(String moduleName) {
        Module module;
        if(Button.NAME.equals(moduleName)) {
            module = new Button();
        } else if(Broadcaster.NAME.equals(moduleName)) {
            module = new Broadcaster();
        } else if (moduleName.startsWith("%")) {
            module = new FlipFlop(moduleName);
        } else {
            module = new Conjunction(moduleName);
        }
        return module;
    }

    public long solveA(int pressTimes) {

        for(int i = 0; i < pressTimes; i++) {
            bfs(modulesByName.get(Broadcaster.NAME), null);
        }

        return lowPulses * highPulses;
    }

    public long solveB() {

        Module button = modulesByName.get(Button.NAME);
        Module broadcaster = modulesByName.get(Broadcaster.NAME);

        List<Module> broadcastAdjacents = graph.get(broadcaster);

        // Find all vertex conjunctions that are linked to the broadcast adjacent module
        // For example: 'c_hd' is linked to 'f_qg'
        Map<Module,Conjunction> moduleToConjunction = findEndConjunctions(broadcastAdjacents);

        // Remove the end vertices conjunctions edge to the inversor conjunction
        // For example: removes the 'c_hd' edge to 'c_tr'
        removeEndConjunctionEdges();

        long result = 1;

        // Test each subgraph and multiply their values:
        // - 'f_qg' to 'c_hd'
        // - 'f_lf' to 'c_tn'
        // - 'f_tb' to 'c_vc'
        // - 'f_dv' to 'c_nh'

        for(Module start : broadcastAdjacents) {
            graph.get(button).clear();
            graph.get(button).add(start);

            boolean found;
            long count = 0;
            do {
                count++;
                found = bfs(start, moduleToConjunction.get(start));
            } while(!found);

            result *= count;
        }

        // Not needed, but just for painting the graph purposes
        broadcastAdjacents.clear();

        return result;
    }


    private Map<Module,Conjunction> findEndConjunctions(List<Module> startModules) {

        Map<Module, Conjunction> result = new HashMap<>();

        List<Module> conjunctions = graph.keySet().stream().filter(Conjunction.class::isInstance).toList();

        for(Module module : conjunctions) {
            Conjunction conjunction = (Conjunction) module;
            List<Module> outputModules = graph.get(conjunction);

            for(Module startModule : startModules) {
                if(outputModules.contains(startModule)) {
                    result.put(startModule, conjunction);
                    break;
                }
            }
        }

        return result;
    }

    private void removeEndConjunctionEdges() {
        List<Module> conjunctions = graph.keySet().stream().filter(Conjunction.class::isInstance).toList();

        for(Module module : conjunctions) {
            Conjunction conjunction = (Conjunction) module;

            // Condiciones especiales:
            // Tienen > 1 salida
            // Tienen > 1 entrada
            if(conjunction.getMemorySize() > 1 && graph.get(conjunction).size() > 1){
                removeConjunctionEdge(conjunction);
            }
        }
    }

    private void removeConjunctionEdge(Conjunction conjunction) {

        List<Module> modifiedOutputs = graph.get(conjunction)
                                            .stream()
                                            .filter(not(Conjunction.class::isInstance))
                                            .toList();

        graph.put(conjunction, modifiedOutputs);

    }

    /// Applies BFS algorithm over all the modules.
    /// During the iterations, it counts how many `high` and `low` pulses are sent,
    /// updating {@link #highPulses} and {@link #lowPulses} counters.
    /// @param start the module that `button` is going to send the first pulse.
    /// @param target module to watch for a certain `low` pulse to appear. Can be `null`,
    /// in that case, no module will be watched.
    /// @return `true` if `target`module has received a `low` pulse. Returns `false` if `target` is `null`
    /// or if it has not received a `low` pulse.
    private boolean bfs(Module start, Module target){
        Queue<PulseEvent> queue = new ArrayDeque<>();
        queue.add(new PulseEvent(Pulse.LOW, modulesByName.get(Button.NAME), start));

        while(!queue.isEmpty()) {
            PulseEvent pulseEvent = queue.poll();

            // Part 2 goal condition
            if(isGoal(pulseEvent, target)) {
                return true;
            }

            // Update high/low counters
            if(pulseEvent.pulse() == Pulse.LOW) {
                lowPulses++;
            } else {
                highPulses++;
            }

            // Process the pulse
            Module currentModule = pulseEvent.destination();
            Optional<Pulse> result = currentModule.process(pulseEvent);

            if(result.isEmpty()) {
                continue;
            }

            // Obtain the result pulse to propagate later
            Pulse outputPulse = result.get();

            // Propagate the pulse to the childs
            List<Module> adjacents = graph.get(currentModule);
            for(Module adjacent : adjacents) {
                PulseEvent newEvent = new PulseEvent(outputPulse, currentModule, adjacent);
                queue.add(newEvent);
            }
        }

        return false;

    }

    /// Checks if a the target module has a ``low`` pulse
    /// If target is null, it returns `false`.
    /// @param pulseEvent the pulse event obtained from the bfs queue.
    /// @param target the module that must be checked to have a `low` pulse.
    /// @return `true` only if the event was generated by the `target` module and the event has a `low` pulse.
    /// Returns `false` otherwise. It also returns `false` if `target` is `null`.
    private boolean isGoal(PulseEvent pulseEvent, Module target) {
        if(target == null) {
            return false;
        }

        return pulseEvent.origin().equals(target) && pulseEvent.pulse() == Pulse.LOW;
    }

}
