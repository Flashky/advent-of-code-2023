package com.adventofcode.flashk.day20.jgrapht;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.jgrapht.modules.Broadcaster;
import com.adventofcode.flashk.day20.jgrapht.modules.Button;
import com.adventofcode.flashk.day20.jgrapht.modules.Conjunction;
import com.adventofcode.flashk.day20.jgrapht.modules.FlipFlop;
import com.adventofcode.flashk.day20.jgrapht.modules.Module;
import com.adventofcode.flashk.day20.jgrapht.modules.Output;
import org.apache.commons.math3.util.ArithmeticUtils;
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
    private Module output;
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
                    output = outputModule;
                }

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

    public long solveB() {

        // TODO si la cosa se complica, extraer la lógica de paint de JGraphT a un método que pueda pintar
        // a partir de mis listas de adyacencia, así, la única fuente de verdad sería la lista de adyacencia, y el grafo
        // de JGraphT solo se pintaría a partir de la misma.

        // Get broadcast links
        List<Module> broadcastAdjacents = graph.get(broadcast);
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

    private Conjunction clearConjunction(String conjunctionName, String outputToRemove) {
        Conjunction conjunction = (Conjunction) graph.keySet().stream().filter(m -> m.getName().equals(conjunctionName)).findFirst().get();

        Module moduleToRemove = graph.keySet().stream().filter(m -> m.getName().equals(outputToRemove)).findFirst().get();
        graph.get(conjunction).remove(moduleToRemove);

        return conjunction;
    }

    private boolean bfs(Module start, Conjunction watcher){
        Queue<PulseEvent> queue = new ArrayDeque<>();
        queue.add(new PulseEvent(Pulse.LOW, button, start));

        while(!queue.isEmpty()) {
            PulseEvent pulseEvent = queue.poll();

            // TODO  quizás aquí
            // Si goal (pulseEvent.origin == th && pulseEvent.pulse == LOW) return true
            if(pulseEvent.origin().equals(watcher) && pulseEvent.pulse() == Pulse.LOW) {
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

    private void bfs(){
        Queue<PulseEvent> queue = new ArrayDeque<>();
        queue.add(new PulseEvent(Pulse.LOW, button, broadcast));

        while(!queue.isEmpty()) {
            PulseEvent pulseEvent = queue.poll();

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
