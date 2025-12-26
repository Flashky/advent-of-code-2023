package com.adventofcode.flashk.day20;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.modules.Broadcaster;
import com.adventofcode.flashk.day20.modules.Button;
import com.adventofcode.flashk.day20.modules.Conjunction;
import com.adventofcode.flashk.day20.modules.FlipFlop;
import com.adventofcode.flashk.day20.modules.Module;
import com.adventofcode.flashk.day20.modules.Output;
import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTExporter;

public class PulsePropagation {

    private static final String WHITESPACES_REGEX = "\\s";

    // Graphs
    private final Graph<Module, DefaultEdge> jgrapht = new DefaultDirectedGraph<>(DefaultEdge.class);
    private final Map<Module, List<Module>> graph = new HashMap<>();

    // Auxiliar structures
    private final Map<String, Module> modulesByName = new HashMap<>();

    // Solution attributes
    private long lowPulses = 0;
    private long highPulses = 0;

    public PulsePropagation(List<String> inputs) {

       // Manually add the button to broadcaster definition to the input
       inputs.add("button -> broadcaster");

       // Initialize graphs
       Map<Module,String[]> outputsByModule = createVertices(inputs);
       createEdges(outputsByModule);

       //paint();
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

            // Add vertex module to the graphs
            jgrapht.addVertex(module);
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
                    jgrapht.addVertex(outputModule);
                    graph.put(outputModule, Collections.emptyList());
                }

                // Add edges to the graphs
                jgrapht.addEdge(module, outputModule);
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

        // Get broadcast links
        List<Module> broadcastAdjacents = graph.get(broadcaster);
        Module qg = broadcastAdjacents.get(2);
        Module lf = broadcastAdjacents.get(3);
        Module tb = broadcastAdjacents.get(0);
        Module dv = broadcastAdjacents.get(1);

        // Clear output nodes

        // Link button with one of them
        // TODO later convert into a loop
        // First input -> 'button' to 'lf' - sink in 'tn'
        graph.get(button).clear();
        graph.get(button).add(qg);
        // 0: tb
        // 1: dv
        // 2: qg
        // 3: lf

        // Find hd and unlink its outputs

        Conjunction hd = clearConjunction("hd","tr");

        long countHd = 0;
        boolean found;
        do {
            countHd++;
            found = bfs(qg, hd);
        } while(!found);


        // hd = 3739 - OK, me sale el mismo 3739 que antes

        // Second input -> 'button' to 'lf' - sink in 'tn'
        graph.get(button).clear();
        graph.get(button).add(lf);

        // Find tn and unlink the output
        Conjunction tn = clearConjunction("tn","xm");

        long countTn = 0;
        do {
            countTn++;
            found = bfs(lf, tn);

        } while(!found);

        // tn = 3761

        // Third input -> 'button' to 'tb' - sink in 'vc'
        graph.get(button).clear();
        graph.get(button).add(tb);

        Conjunction vc = clearConjunction("vc","dr");

        long countTb = 0;
        do {
            countTb++;
            found = bfs(tb, vc);

        } while(!found);

        // tb = 3797

        // Fourth input -> 'button' to 'dv' - sink in 'jx' - remove 'nh' from 'jx'
        graph.get(button).clear();
        graph.get(button).add(dv);

        Conjunction jx = clearConjunction("jx","nh");

        long countDv = 0;
        do {
            countDv++;
            found = bfs(dv, jx);

        } while(!found);

        // dv = 3889

        // All four numbers are prime, so the result lcm of all 4 numbers is just the multiplication between them.
        return countHd * countDv * countTb * countTn;
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

    ///  Removes an edge between the conjunction origin and destination from the graph
    /// @param conjunctionName the origin module name.
    /// @param outputToRemove the destination module name
    /// @return
    private Conjunction clearConjunction(String conjunctionName, String outputToRemove) {
        Conjunction conjunction = (Conjunction) modulesByName.get(conjunctionName);
        Module moduleToRemove = modulesByName.get(outputToRemove);
        graph.get(conjunction).remove(moduleToRemove);

        return conjunction;
    }

    public void paint() {
        DOTExporter<Module, DefaultEdge> exporter=new DOTExporter<>(Module::getLabel);
        Writer writer = new StringWriter();
        exporter.exportGraph(jgrapht, writer);
        System.out.println(writer);
    }
}
