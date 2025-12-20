package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class AdjacentStrategyUltraCrucible extends AdjacentStrategy {

    public AdjacentStrategyUltraCrucible(ClumsyCrucible clumsyCrucible) {
        super(clumsyCrucible);
    }

    public Set<Node> getInitialNodes() {
        Set<Node> nodes = new HashSet<>();

        if(clumsyCrucible.getRows() >= 4) {
            int heatloss = 0;
            for (int row = 1; row <= 4; row++) {
                heatloss += clumsyCrucible.getMap()[row][0];
            }
            NodeIdentifier downId = new NodeIdentifier(0,4, new Vector2(0,1), 4);
            Node downNode = new Node(downId, heatloss);
            downNode.setTotalHeatloss(heatloss);
            nodes.add(downNode);
        }

        if(clumsyCrucible.getCols() >= 4) {
            int heatloss = 0;
            for (int col = 1; col <= 4; col++) {
                heatloss += clumsyCrucible.getMap()[0][col];
            }
            NodeIdentifier rightId = new NodeIdentifier(4,0,new Vector2(1,0), 4);
            Node rightNode = new Node(rightId, heatloss);
            rightNode.setTotalHeatloss(heatloss);
            nodes.add(rightNode);
        }

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
        if(id.steps() < 10) {
            getStraightNode(id).ifPresent(adjacents::add);
        }

        return adjacents;

    }

    private Optional<Node> getSideNode(Vector2 newDir, int x, int y) {
        Vector2 maxDir;
        Vector2 maxPos;
        maxDir = new Vector2(newDir);
        maxDir.multiply(4);

        maxPos = new Vector2(x, y);
        maxPos.transform(maxDir);

        if(clumsyCrucible.isInbounds(maxPos)) {
            Vector2 newPos = new Vector2(x, y);

            int heatloss = 0;
            for (int i = 0; i < 4; i++) {
                newPos.transform(newDir);
                heatloss += clumsyCrucible.getMap()[newPos.getY()][newPos.getX()];
            }

            NodeIdentifier newId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 4);
            Node newNode = clumsyCrucible.getGraphNodes().getOrDefault(newId, new Node(newId, heatloss));
            clumsyCrucible.getGraphNodes().putIfAbsent(newId, newNode);
            return Optional.of(newNode);
        }
        return Optional.empty();
    }
}
