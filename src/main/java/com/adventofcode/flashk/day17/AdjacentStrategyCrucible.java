package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class AdjacentStrategyCrucible extends AdjacentStrategy {

    public AdjacentStrategyCrucible(ClumsyCrucible clumsyCrucible) {
        super(clumsyCrucible);
    }

    public Set<Node> getInitialNodes() {

        Set<Node> nodes = new HashSet<>();
        NodeIdentifier downId = new NodeIdentifier(0,1,new Vector2(0,1), 1);
        Node downNode = new Node(downId, clumsyCrucible.getMap()[1][0]);
        downNode.setTotalHeatloss(downNode.getHeatloss());
        nodes.add(downNode);

        NodeIdentifier rightId = new NodeIdentifier(1,0,new Vector2(1,0), 1);
        Node rightNode = new Node(rightId, clumsyCrucible.getMap()[0][1]);
        rightNode.setTotalHeatloss(rightNode.getHeatloss());
        nodes.add(rightNode);

        return nodes;
    }

    @Override
    public Set<Node> getAdjacents(Node currentNode) {
        Set<Node> adjacents = new HashSet<>();

        // Current identifier data
        NodeIdentifier id = currentNode.getId();

        // Left
        Vector2 newDir = new Vector2(id.dir());
        newDir.rotateLeft();
        getSideNode(newDir, id.x(), id.y()).ifPresent(adjacents::add);

        // Right
        newDir = new Vector2(id.dir());
        newDir.rotateRight();
        getSideNode(newDir, id.x(), id.y()).ifPresent(adjacents::add);

        // Straight
        if(id.steps() < 3) {
            getStraightNode(currentNode.getId()).ifPresent(adjacents::add);
        }

        return adjacents;
    }

    private Optional<Node> getSideNode(Vector2 newDir, int x, int y) {
        Vector2 newPos = new Vector2(x, y);
        newPos.transform(newDir);

        if(clumsyCrucible.isInbounds(newPos)) {
            NodeIdentifier leftId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 1);
            Node leftNode = clumsyCrucible.getGraphNodes().getOrDefault(leftId, new Node(leftId, clumsyCrucible.getMap()[newPos.getY()][newPos.getX()]));
            clumsyCrucible.getGraphNodes().putIfAbsent(leftId, leftNode);
            return Optional.of(leftNode);
        }

        return Optional.empty();
    }
}
