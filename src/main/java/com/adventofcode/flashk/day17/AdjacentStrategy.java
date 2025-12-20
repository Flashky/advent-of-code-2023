package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public abstract class AdjacentStrategy {

    protected final ClumsyCrucible clumsyCrucible;

    public AdjacentStrategy(ClumsyCrucible clumsyCrucible) {
        this.clumsyCrucible = clumsyCrucible;
    }

    public abstract Set<Node> getInitialNodes();
    public abstract Set<Node> getAdjacents(Node currentNode);

    /**
     * Attempts to generate a straight node based on the original NodeIdentifier
     * @param id the node identifier to generate a new node.
     * @return an optional with a node if the node can be created following the straight line. An optional empty otherwise
     */
    protected Optional<Node> getStraightNode(NodeIdentifier id) {
        Vector2 newPos = new Vector2(id.x(), id.y());
        newPos.transform(id.dir());

        if(clumsyCrucible.isInbounds(newPos)) {
            NodeIdentifier straightId = new NodeIdentifier(newPos.getX(), newPos.getY(), id.dir(), id.steps()+1);
            Node straightNode = clumsyCrucible.getGraphNodes().getOrDefault(straightId, new Node(straightId, clumsyCrucible.getMap()[newPos.getY()][newPos.getX()]));
            clumsyCrucible.getGraphNodes().putIfAbsent(straightId, straightNode);
            return Optional.of(straightNode);
        }

        return Optional.empty();
    }

}
