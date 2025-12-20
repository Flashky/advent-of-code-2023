package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class AdjacentStrategyUltraCrucible extends AdjacentStrategy {
    
    public AdjacentStrategyUltraCrucible(ClumsyCrucible clumsyCrucible) {
        super(clumsyCrucible);
    }
    
    @Override
    public Set<Node> getAdjacents(Node currentNode) {
        Set<Node> adjacents = new HashSet<>();

        // Current identifier data
        int x = currentNode.getId().x();
        int y = currentNode.getId().y();
        Vector2 dir = currentNode.getId().dir();
        int steps = currentNode.getId().steps();


        // Left
        Vector2 newDir = new Vector2(dir);
        newDir.rotateLeft();

        Vector2 maxDir = new Vector2(newDir);
        maxDir.multiply(4);

        Vector2 maxPos = new Vector2(x, y);
        maxPos.transform(maxDir);

        if(clumsyCrucible.isInbounds(maxPos)) {
            Vector2 newPos = new Vector2(x, y);

            int heatloss = 0;
            for (int i = 0; i < 4; i++) {
                newPos.transform(newDir);
                heatloss += clumsyCrucible.getMap()[newPos.getY()][newPos.getX()];
            }

            NodeIdentifier leftId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 4);
            Node leftNode = clumsyCrucible.getGraphNodes().getOrDefault(leftId, new Node(leftId, heatloss));
            clumsyCrucible.getGraphNodes().putIfAbsent(leftId, leftNode);
            adjacents.add(leftNode);

        }


        // Right
        newDir = new Vector2(dir);
        newDir.rotateRight();

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

            NodeIdentifier rightId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 4);
            Node rightNode = clumsyCrucible.getGraphNodes().getOrDefault(rightId, new Node(rightId, heatloss));
            clumsyCrucible.getGraphNodes().putIfAbsent(rightId, rightNode);
            adjacents.add(rightNode);
        }

        // Straight
        if(steps == 10) {
            return adjacents;
        }

        Vector2 newPos = new Vector2(x,y);
        newPos.transform(dir);

        if(clumsyCrucible.isInbounds(newPos)) {
            NodeIdentifier straightId = new NodeIdentifier(newPos.getX(), newPos.getY(), dir, steps+1);
            Node straightNode = clumsyCrucible.getGraphNodes().getOrDefault(straightId, new Node(straightId, clumsyCrucible.getMap()[newPos.getY()][newPos.getX()]));
            clumsyCrucible.getGraphNodes().putIfAbsent(straightId, straightNode);
            adjacents.add(straightNode);
        }

        return adjacents;

    }
}
