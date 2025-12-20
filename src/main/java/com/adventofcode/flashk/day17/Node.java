package com.adventofcode.flashk.day17;

import static java.lang.IO.println;

import module java.base;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.jetbrains.annotations.NotNull;

@Getter
public class Node implements Comparable<Node> {

    private final NodeIdentifier id;

    // Dijkstra attributes:
    // - distance (in this case, as heatloss)
    // - totalDistance (in this case, the totalHeatloss)
    // - isVisited

    private final int heatloss;

    @Setter
    private long totalHeatloss = Long.MAX_VALUE;

    @Setter
    private boolean isVisited = false;

    public Node(NodeIdentifier id, int heatloss) {
        this.id = id;
        this.heatloss = heatloss;
    }

    @Override
    public int compareTo(@NotNull Node other) {
        return Long.compare(totalHeatloss, other.totalHeatloss);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Node node)) return false;

        return new EqualsBuilder().append(id, node.id).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).toHashCode();
    }
}
