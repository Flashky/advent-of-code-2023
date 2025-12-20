package com.adventofcode.flashk.day17;


import module java.base;
import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;

@Getter
public class ClumsyCrucible {

    private final int[][] map;
    private final int rows;
    private final int cols;
    private final Map<NodeIdentifier, Node> graphNodes = new HashMap<>();

    public ClumsyCrucible(int[][] inputs) {
        rows = inputs.length;
        cols = inputs[0].length;
        map = inputs;
    }

    public long solve(AdjacentStrategy strategy) {
        dijkstra(strategy);
        return getMinHeatloss();
    }

    private void dijkstra(AdjacentStrategy adjacentStrategy) {

        // Initialize initial nodes and add them to the graph
        PriorityQueue<Node> nodes = new PriorityQueue<>();
        Set<Node> initialNodes = adjacentStrategy.getInitialNodes();

        for(Node initialNode: initialNodes) {
            nodes.add(initialNode);
            graphNodes.put(initialNode.getId(), initialNode);
        }

        while(!nodes.isEmpty()) {
            Node nextNode = nodes.poll();
            nextNode.setVisited(true);

            Set<Node> adjacents = adjacentStrategy.getAdjacents(nextNode);
            for(Node adjacent : adjacents) {
                if(!adjacent.isVisited()) {
                    // Typical dijkstra: d(v) > d(u) + weight(u,v)
                    if(adjacent.getTotalHeatloss() > nextNode.getTotalHeatloss() + adjacent.getHeatloss()) {
                        adjacent.setTotalHeatloss(nextNode.getTotalHeatloss() + adjacent.getHeatloss());
                        nodes.add(adjacent);
                    }
                }
            }
        }

    }

    private long getMinHeatloss() {

        int x = cols - 1;
        int y = rows - 1;

        Set<NodeIdentifier> endNodeIdentifiers = graphNodes.keySet().stream()
                .filter(ni -> ni.x() == x)
                .filter(ni -> ni.y() == y)
                .collect(Collectors.toSet());

        long minHeatloss = Long.MAX_VALUE;
        for(NodeIdentifier endNodeIdentifier : endNodeIdentifiers) {
            minHeatloss = Math.min(minHeatloss, graphNodes.get(endNodeIdentifier).getTotalHeatloss());
        }

        return minHeatloss;
    }

    public boolean isInbounds(Vector2 pos) {
        return (pos.getY() >= 0 && pos.getY() < rows && pos.getX() >= 0 && pos.getX() < cols);
    }

    public Node getOrCreateNode(NodeIdentifier nodeIdentifier, int heatloss) {
        Node newNode = graphNodes.getOrDefault(nodeIdentifier, new Node(nodeIdentifier, heatloss));
        graphNodes.putIfAbsent(nodeIdentifier, newNode);
        return newNode;
    }
}
