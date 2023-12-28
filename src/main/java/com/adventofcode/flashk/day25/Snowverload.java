package com.adventofcode.flashk.day25;

import org.apache.commons.lang3.StringUtils;
import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.List;

public class Snowverload {

    private final Graph<String, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);

    public Snowverload(List<String> inputs) {

        for(String input : inputs) {
            String[] inputParts = input.split(":");
            String leftVertex = inputParts[0];
            String[] rightVertexes = inputParts[1].stripLeading().split(StringUtils.SPACE);

            graph.addVertex(leftVertex);
            for(String rightVertex : rightVertexes) {
                graph.addVertex(rightVertex);
                graph.addEdge(leftVertex, rightVertex);
            }
        }

    }

    public int solve() {

        StoerWagnerMinimumCut<String,DefaultEdge> minCut = new StoerWagnerMinimumCut<>(graph);

        int totalVertexes = graph.vertexSet().size();
        int cutSize = minCut.minCut().size();
        int remainingSize = totalVertexes - cutSize;

        return cutSize * remainingSize;
    }

}
