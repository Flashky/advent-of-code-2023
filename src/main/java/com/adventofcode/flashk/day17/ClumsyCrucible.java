package com.adventofcode.flashk.day17;


import module java.base;
import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;

public class ClumsyCrucible {

    @Getter
    private final int[][] map;
    private final int rows;
    private final int cols;

    @Getter
    private final Map<NodeIdentifier, Node> graphNodes = new HashMap<>();
    private final Node start;

    public ClumsyCrucible(int[][] inputs) {

        rows = inputs.length;
        cols = inputs[0].length;
        map = inputs;

        // Create start node and add it to the graph
        NodeIdentifier startId = new NodeIdentifier(0,0, new Vector2(0,1), 0);
        start = new Node(startId, map[0][0]);
        graphNodes.put(startId, start);

    }

    public long solveA() {
        dijkstra(new AdjacentStrategyCrucible(this));
        return getMinHeatloss();
    }

    public long solveB() {
        dijkstra(new AdjacentStrategyUltraCrucible(this));
        return getMinHeatloss();
    }


    private void dijkstra(AdjacentStrategy adjacentStrategy) {

        PriorityQueue<Node> nodes = new PriorityQueue<>();
        start.setTotalHeatloss(0);
        nodes.add(start);

        while(!nodes.isEmpty()) {
            Node nextNode = nodes.poll();
            nextNode.setVisited(true);

            Set<Node> adjacents = adjacentStrategy.getAdjacents(nextNode);
            for(Node adjacent : adjacents) {
                if(!adjacent.isVisited()) {
                    // Typical dijkstra: d(v) > d(u) + weight(u,v)
                    if(adjacent.getTotalHeatloss() > nextNode.getTotalHeatloss() + adjacent.getHeatloss()) {
                        adjacent.setTotalHeatloss(nextNode.getTotalHeatloss() + adjacent.getHeatloss());
                        // Optional: set parent
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
}
