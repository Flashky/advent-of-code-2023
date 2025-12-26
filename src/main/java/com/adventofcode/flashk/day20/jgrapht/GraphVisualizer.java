package com.adventofcode.flashk.day20.jgrapht;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.day20.modules.Module;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.dot.DOTExporter;

public class GraphVisualizer {

    private final Graph<Module, DefaultEdge> jgrapht = new DefaultDirectedGraph<>(DefaultEdge.class);

    public GraphVisualizer(Map<Module, List<Module>> graph) {

        // Add al vertices to the jgraph graph
        graph.keySet().forEach(jgrapht::addVertex);

        // Add all edges to the jgrapht graph
        for(Module origin : graph.keySet()) {
            graph.get(origin).forEach(destination -> jgrapht.addEdge(origin, destination));
        }
     }

    public String paint(boolean debug) {
        DOTExporter<Module, DefaultEdge> exporter = new DOTExporter<>(Module::getLabel);
        Writer writer = new StringWriter();
        exporter.exportGraph(jgrapht, writer);

        if(debug) {
            println(writer);
        }

        return writer.toString();
    }
}
