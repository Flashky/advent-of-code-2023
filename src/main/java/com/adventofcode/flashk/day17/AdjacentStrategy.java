package com.adventofcode.flashk.day17;

import module java.base;

public abstract class AdjacentStrategy {

    protected final ClumsyCrucible clumsyCrucible;

    public AdjacentStrategy(ClumsyCrucible clumsyCrucible) {
        this.clumsyCrucible = clumsyCrucible;
    }

    public abstract Set<Node> getAdjacents(Node currentNode);

}
