package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class AdjacentStrategyCrucible extends AdjacentStrategy {

    public AdjacentStrategyCrucible(ClumsyCrucible clumsyCrucible) {
        super(clumsyCrucible, 1,3);
    }

    public Set<Node> getInitialNodes() {

        Set<Node> nodes = new HashSet<>();
        NodeIdentifier downId = new NodeIdentifier(0,minSteps,new Vector2(0,1), minSteps);
        Node downNode = new Node(downId, clumsyCrucible.getMap()[minSteps][0]);
        downNode.setTotalHeatloss(downNode.getHeatloss());
        nodes.add(downNode);

        NodeIdentifier rightId = new NodeIdentifier(minSteps,0,new Vector2(1,0), minSteps);
        Node rightNode = new Node(rightId, clumsyCrucible.getMap()[0][minSteps]);
        rightNode.setTotalHeatloss(rightNode.getHeatloss());
        nodes.add(rightNode);

        return nodes;
    }

    @Override
    protected Optional<Node> getSideNode(Vector2 newDir, int x, int y) {
        Vector2 newPos = new Vector2(x, y);
        newPos.transform(newDir);

        if(clumsyCrucible.isInbounds(newPos)) {
            NodeIdentifier leftId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, minSteps);
            Node leftNode = clumsyCrucible.getGraphNodes().getOrDefault(leftId, new Node(leftId, clumsyCrucible.getMap()[newPos.getY()][newPos.getX()]));
            clumsyCrucible.getGraphNodes().putIfAbsent(leftId, leftNode);
            return Optional.of(leftNode);
        }

        return Optional.empty();
    }
}
