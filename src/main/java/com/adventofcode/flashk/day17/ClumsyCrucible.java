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

        // Create start node and add it to the graph
        NodeIdentifier startId = new NodeIdentifier(0,0, new Vector2(0,0), 0);
       // start = new Node(startId, map[0][0]);
       // graphNodes.put(startId, start);

    }

    public long solveA() {

        /*
        NodeIdentifier downId = new NodeIdentifier(0,1,new Vector2(0,1), 1);
        Node downNode = new Node(downId, map[1][0]);
        downNode.setTotalHeatloss(downNode.getHeatloss());
        initialNodes.add(downNode);
        graphNodes.put(downId, downNode);

        NodeIdentifier rightId = new NodeIdentifier(1,0,new Vector2(1,0), 1);
        Node rightNode = new Node(rightId, map[0][1]);
        rightNode.setTotalHeatloss(rightNode.getHeatloss());
        initialNodes.add(rightNode);
        graphNodes.put(rightId, rightNode);*/

        NodeIdentifier startId = new NodeIdentifier(0,0, new Vector2(1,0), 1);
        Node start = new Node(startId, map[0][0]);
        start.setTotalHeatloss(0);
        graphNodes.put(startId, start);

        dijkstra(new AdjacentStrategyCrucible(this));
        return getMinHeatloss();
    }

    public long solveB() {

        // Calculate initial nodes
        PriorityQueue<Node> initialNodes = new PriorityQueue<>();

        /*
        if(rows >= 4) {
            int heatloss = 0;
            for (int row = 0; row < 4; row++) {
                heatloss += map[row][0];
            }
            NodeIdentifier downId = new NodeIdentifier(0,4, new Vector2(0,1), 4);
            Node downNode = new Node(downId, heatloss);
            downNode.setTotalHeatloss(heatloss);
            initialNodes.add(downNode);
            graphNodes.put(downId, downNode);
        }

        if(cols >= 4) {
            int heatloss = 0;
            for (int col = 0; col < 4; col++) {
                heatloss += map[0][col];
            }
            NodeIdentifier rightId = new NodeIdentifier(4,0,new Vector2(1,0), 4);
            Node rightNode = new Node(rightId, heatloss);
            rightNode.setTotalHeatloss(heatloss);
            initialNodes.add(rightNode);
            graphNodes.put(rightId, rightNode);
        }*/
        dijkstra(new AdjacentStrategyUltraCrucible(this));

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
