package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class AdjacentStrategyUltraCrucible extends AdjacentStrategy {

    public AdjacentStrategyUltraCrucible(ClumsyCrucible clumsyCrucible) {
        super(clumsyCrucible, 4, 10);
    }

    public Set<Node> getInitialNodes() {
        Set<Node> nodes = new HashSet<>();

        if(clumsyCrucible.getRows() >= minSteps) {
            int heatloss = 0;
            for (int row = 1; row <= minSteps; row++) {
                heatloss += clumsyCrucible.getMap()[row][0];
            }
            NodeIdentifier downId = new NodeIdentifier(0, minSteps, new Vector2(0,1), minSteps);
            Node downNode = new Node(downId, heatloss);
            downNode.setTotalHeatloss(heatloss);
            nodes.add(downNode);
        }

        if(clumsyCrucible.getCols() >= minSteps) {
            int heatloss = 0;
            for (int col = 1; col <= minSteps; col++) {
                heatloss += clumsyCrucible.getMap()[0][col];
            }
            NodeIdentifier rightId = new NodeIdentifier(minSteps,0,new Vector2(1,0), minSteps);
            Node rightNode = new Node(rightId, heatloss);
            rightNode.setTotalHeatloss(heatloss);
            nodes.add(rightNode);
        }

        return nodes;
    }

    @Override
    protected Optional<Node> getSideNode(Vector2 newDir, int x, int y) {
        Vector2 maxDir = new Vector2(newDir);
        maxDir.multiply(minSteps);

        Vector2 maxPos = new Vector2(x, y);
        maxPos.transform(maxDir);

        if(clumsyCrucible.isInbounds(maxPos)) {
            Vector2 newPos = new Vector2(x, y);

            int heatloss = 0;
            for (int i = 0; i < minSteps; i++) {
                newPos.transform(newDir);
                heatloss += clumsyCrucible.getMap()[newPos.getY()][newPos.getX()];
            }

            NodeIdentifier newId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, minSteps);
            Node newNode = clumsyCrucible.getOrCreateNode(newId, heatloss);

            return Optional.of(newNode);
        }
        return Optional.empty();
    }
}
