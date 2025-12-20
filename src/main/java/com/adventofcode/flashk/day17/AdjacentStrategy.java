package com.adventofcode.flashk.day17;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public abstract class AdjacentStrategy {

    protected final ClumsyCrucible clumsyCrucible;
    protected int minSteps;
    protected int maxSteps;

    public AdjacentStrategy(ClumsyCrucible clumsyCrucible, int minSteps, int maxSteps) {
        this.clumsyCrucible = clumsyCrucible;
        this.minSteps = minSteps;
        this.maxSteps = maxSteps;
    }

    public Set<Node> getAdjacents(Node currentNode){
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
        if(id.steps() < maxSteps) {
            getStraightNode(id).ifPresent(adjacents::add);
        }

        return adjacents;
    }

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

    public abstract Set<Node> getInitialNodes();
    protected abstract Optional<Node> getSideNode(Vector2 newDir, int x, int y);



}
