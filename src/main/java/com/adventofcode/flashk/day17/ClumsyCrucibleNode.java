package com.adventofcode.flashk.day17;

import static java.lang.IO.println;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class ClumsyCrucibleNode {

    private final int[][] map;
    private final int rows;
    private final int cols;
    private final Map<NodeIdentifier, Node> graphNodes = new HashMap<>();
    private final Node start;

    public ClumsyCrucibleNode(int[][] inputs) {

        rows = inputs.length;
        cols = inputs[0].length;
        map = new int[this.rows][];

        for(int row = 0; row < rows; row++) {
            this.map[row] = inputs[row];
        }

        // Create start node and add it to the graph
        NodeIdentifier startId = new NodeIdentifier(0,0, new Vector2(0,1), 0);
        start = new Node(startId, map[0][0]);
        graphNodes.put(startId, start);

    }

    public long solveA() {

        long result = 0;

        PriorityQueue<Node> nodes = new PriorityQueue<>();
        start.setTotalHeatloss(0);
        nodes.add(start);

        while(!nodes.isEmpty()) {
            Node nextNode = nodes.poll();
            nextNode.setVisited(true);

            Set<Node> adjacents = getAdjacents(nextNode);
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

        // There are 12 posible solutions at end node find the smallest one
        Set<Node> endNodes = getEndNodes();
        Optional<Node> smallestEndNode = endNodes.stream().sorted().findFirst();
        return smallestEndNode.map(Node::getTotalHeatloss).orElse(-1L);

    }

    private Set<Node> getAdjacents(Node currentNode) {

        Set<Node> adjacents = new HashSet<>();

        // Current identifier data
        int x = currentNode.getId().x();
        int y = currentNode.getId().y();
        Vector2 dir = currentNode.getId().dir();
        int steps = currentNode.getId().steps();


        // Left
        Vector2 newDir = new Vector2(dir);
        newDir.rotateLeft();

        Vector2 newPos = new Vector2(x,y);
        newPos.transform(newDir);

        if(isInbounds(newPos)) {
            NodeIdentifier leftId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 1);
            Node leftNode = graphNodes.getOrDefault(leftId, new Node(leftId, map[newPos.getY()][newPos.getX()]));
            graphNodes.putIfAbsent(leftId, leftNode);
            adjacents.add(leftNode);
        }

        newPos.rotateLeft();

        // Right
        newDir = new Vector2(dir);
        newDir.rotateRight();

        newPos = new Vector2(x,y);
        newPos.transform(newDir);

        if(isInbounds(newPos)) {
            NodeIdentifier rightId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 1);
            Node rightNode = graphNodes.getOrDefault(rightId, new Node(rightId, map[newPos.getY()][newPos.getX()]));
            graphNodes.putIfAbsent(rightId, rightNode);
            adjacents.add(rightNode);
        }

        // Straight
        if(steps == 3) {
            return adjacents;
        }

        newPos = new Vector2(x,y);
        newPos.transform(dir);

        if(isInbounds(newPos)) {
            NodeIdentifier straightId = new NodeIdentifier(newPos.getX(), newPos.getY(), dir, steps+1);
            Node straightNode = graphNodes.getOrDefault(straightId, new Node(straightId, map[newPos.getY()][newPos.getX()]));
            graphNodes.putIfAbsent(straightId, straightNode);
            adjacents.add(straightNode);
        }

        return adjacents;

    }

    private Set<Node> getEndNodes() {

        Set<Node> endNodes = new HashSet<>();

        Set<Vector2> directions = Set.of(Vector2.up(), Vector2.down(), Vector2.left(), Vector2.right());

        int x = cols - 1;
        int y = rows - 1;

        for(int step = 1; step <= 3; step++) {
            for(Vector2 dir : directions) {
                NodeIdentifier nodeIdentifier = new NodeIdentifier(x,y, dir, step);
                if(graphNodes.containsKey(nodeIdentifier)) {
                    endNodes.add(graphNodes.get(nodeIdentifier));
                }
            }
        }

        return endNodes;
    }

    private boolean isInbounds(Vector2 pos) {
        return (pos.getY() >= 0 && pos.getY() < rows && pos.getX() >= 0 && pos.getX() < cols);
    }
}
