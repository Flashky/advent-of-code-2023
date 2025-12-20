package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class AdjacentStrategyCrucible extends AdjacentStrategy {

    public AdjacentStrategyCrucible(ClumsyCrucible clumsyCrucible) {
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

        // Start node only
        if((currentNode.getId().dir().getX() == 0 && currentNode.getId().dir().getY() == 0)) {
            Vector2 pos = new Vector2(1,0);
            if(clumsyCrucible.isInbounds(pos)) {
                NodeIdentifier id = new NodeIdentifier(1, 0, pos, 1);
                adjacents.add(new Node(id, clumsyCrucible.getMap()[0][1]));
            }

            pos = new Vector2(0,1);
            if(clumsyCrucible.isInbounds(pos)) {
                NodeIdentifier id = new NodeIdentifier(0, 1, pos, 1);
                adjacents.add(new Node(id, clumsyCrucible.getMap()[1][0]));
            }
            return adjacents;
        }

        // Left
        Vector2 newDir = new Vector2(dir);
        newDir.rotateLeft();

        Vector2 newPos = new Vector2(x,y);
        newPos.transform(newDir);

        if(clumsyCrucible.isInbounds(newPos)) {
            NodeIdentifier leftId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 1);
            Node leftNode = clumsyCrucible.getGraphNodes().getOrDefault(leftId, new Node(leftId, clumsyCrucible.getMap()[newPos.getY()][newPos.getX()]));
            clumsyCrucible.getGraphNodes().putIfAbsent(leftId, leftNode);
            adjacents.add(leftNode);
        }

        // Right
        newDir = new Vector2(dir);
        newDir.rotateRight();

        newPos = new Vector2(x,y);
        newPos.transform(newDir);

        if(clumsyCrucible.isInbounds(newPos)) {
            NodeIdentifier rightId = new NodeIdentifier(newPos.getX(), newPos.getY(), newDir, 1);
            Node rightNode = clumsyCrucible.getGraphNodes().getOrDefault(rightId, new Node(rightId, clumsyCrucible.getMap()[newPos.getY()][newPos.getX()]));
            clumsyCrucible.getGraphNodes().putIfAbsent(rightId, rightNode);
            adjacents.add(rightNode);
        }

        // Straight
        if(steps == 3) {
            return adjacents;
        }

        newPos = new Vector2(x,y);
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
