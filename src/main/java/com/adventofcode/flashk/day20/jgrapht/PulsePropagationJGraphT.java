package com.adventofcode.flashk.day20.jgrapht;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.modules.Broadcaster;
import com.adventofcode.flashk.day20.jgrapht.modules.Button;
import com.adventofcode.flashk.day20.jgrapht.modules.Conjunction;
import com.adventofcode.flashk.day20.jgrapht.modules.FlipFlop;
import com.adventofcode.flashk.day20.jgrapht.modules.Module;
import com.adventofcode.flashk.day20.jgrapht.modules.Output;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.builder.GraphTypeBuilder;
import org.jgrapht.nio.dot.DOTExporter;

public class PulsePropagationJGraphT {

    private static final String WHITESPACES_REGEX = "\\s";
    private final Graph<com.adventofcode.flashk.day20.jgrapht.modules.Module, DefaultEdge> jgrapht;

    private Map<Module, List<Module>> graph = new HashMap<>();
    private Module button;
    private Module broadcast;

    private long lowPulses = 0;
    private long highPulses = 0;

    public PulsePropagationJGraphT(List<String> inputs) {

       //Initialize graph
       jgrapht = GraphTypeBuilder.<com.adventofcode.flashk.day20.jgrapht.modules.Module, DefaultEdge>directed()
                        .allowingMultipleEdges(true)
                        .allowingSelfLoops(true)
                        .edgeClass(DefaultEdge.class)
                        .buildGraph();

       // Helper structures
       Map<String,Module> modulesByName = new HashMap<>();
       Map<Module,String[]> outputsByModule =new HashMap<>();

       // Graph creation
       createVertices(inputs, modulesByName, outputsByModule);
       createEdges(outputsByModule, modulesByName);

       //paint();
    }

    private void createVertices(List<String> inputs, Map<String, Module> modulesByName, Map<Module, String[]> outputsByModule) {

        // Create the button
        String[] buttonOutput = { "broadcaster" };
        Button button = new Button();
        modulesByName.put(button.getName(), button);
        outputsByModule.put(button, buttonOutput);
        jgrapht.addVertex(button);
        graph.put(button, new ArrayList<>());
        this.button = button;

        // Create all the vertices
        for(String input : inputs) {
            String[] splittedInput = input.split(" -> ");
            String moduleName = splittedInput[0];
            String[] outputs = splittedInput[1].replaceAll(WHITESPACES_REGEX, "").split(",");

            com.adventofcode.flashk.day20.jgrapht.modules.Module module;
            if("broadcaster".equals(moduleName)) {
                module = new Broadcaster();
                broadcast = module;
            } else if (moduleName.startsWith("%")) {
                module = new FlipFlop(moduleName);
            } else {
                module = new Conjunction(moduleName);
            }

            modulesByName.put(module.getName(), module);
            outputsByModule.put(module, outputs);
            jgrapht.addVertex(module);
            graph.put(module, new ArrayList<>());

        }
    }

    private void createEdges(Map<Module, String[]> outputsByModule, Map<String, Module> modulesByName) {
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
                //println(module.getName() + " -> " + name);
                jgrapht.addEdge(module, outputModule);
                graph.get(module).add(outputModule);

                // If it is a conjunction module, add the input to its memory
                if(outputModule instanceof Conjunction) {
                    ((Conjunction) outputModule).addInputModule(module);
                }
            }
        }
    }

    public long solveA(int pressTimes) {
        for(int i = 0; i < pressTimes; i++) {
            bfs();
        }

        // TODO calcular función solución
        long result = lowPulses * highPulses;
        return result;
    }

    private void bfs(){
        Queue<PulseEvent> queue = new ArrayDeque<>();
        queue.add(new PulseEvent(Pulse.LOW, button, broadcast));
        //start.setVisited(true); // TODO ver más tarde si necesitamos visited

        while(!queue.isEmpty()) {
            PulseEvent pulseEvent = queue.poll();
            println(pulseEvent.origin().getName() +" -"+pulseEvent.pulse()+"-> "+pulseEvent.destination().getName());

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

    }

    public void paint() {
        DOTExporter<Module, DefaultEdge> exporter=new DOTExporter<>(m -> m.getLabel());
        Writer writer = new StringWriter();
        exporter.exportGraph(jgrapht, writer);
        System.out.println(writer);
    }
}
