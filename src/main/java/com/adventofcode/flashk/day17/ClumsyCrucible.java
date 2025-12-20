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

        // Calculate the minimum possible solution
        Set<Node> endNodes = getEndNodes(1, 3);
        Optional<Node> smallestEndNode = endNodes.stream().sorted().findFirst();
        return smallestEndNode.map(Node::getTotalHeatloss).orElse(-1L);
    }

    public long solveB() {
        dijkstra(new AdjacentStrategyUltraCrucible(this));

        // Calculate the minimum possible solution
        Set<Node> endNodes = getEndNodes(4, 10);
        Optional<Node> smallestEndNode = endNodes.stream().sorted().findFirst();
        return smallestEndNode.map(Node::getTotalHeatloss).orElse(-1L);
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

    private Set<Node> getEndNodes(int minSteps, int maxSteps) {

        Set<Node> endNodes = new HashSet<>();

        Set<Vector2> directions = Set.of(Vector2.up(), Vector2.down(), Vector2.left(), Vector2.right());

        int x = cols - 1;
        int y = rows - 1;

        // TODO puede que este step = 1 sea el que esté causando un problema, el step mínimo en parte 2 es 4.
        for(int step = minSteps; step <= maxSteps; step++) {
            for(Vector2 dir : directions) {
                NodeIdentifier nodeIdentifier = new NodeIdentifier(x,y, dir, step);
                if(graphNodes.containsKey(nodeIdentifier)) {
                    endNodes.add(graphNodes.get(nodeIdentifier));
                }
            }
        }

        return endNodes;
    }

    private Set<Node> getEndNodesPart2() {


        Set<Node> endNodes = new HashSet<>();

        Set<Vector2> directions = Set.of(Vector2.up(), Vector2.down(), Vector2.left(), Vector2.right());

        int x = cols - 1;
        int y = rows - 1;

        for(int step = 1; step <= 10; step++) {
            for(Vector2 dir : directions) {
                NodeIdentifier nodeIdentifier = new NodeIdentifier(x,y, dir, step);
                if(graphNodes.containsKey(nodeIdentifier)) {
                    endNodes.add(graphNodes.get(nodeIdentifier));
                }
            }
        }

        return endNodes;
    }

    public boolean isInbounds(Vector2 pos) {
        return (pos.getY() >= 0 && pos.getY() < rows && pos.getX() >= 0 && pos.getX() < cols);
    }
}
