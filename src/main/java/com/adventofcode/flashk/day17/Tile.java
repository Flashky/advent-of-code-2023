package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Tile implements Comparable<Tile>{

    private int row;
    private int col;
    private int totalHeatloss = Integer.MAX_VALUE;
    private int heatloss;

    private final Set<Pair<Vector2, Integer>> visitedDirectionsPerLength = new HashSet<>();

    public Tile(int heatloss, int row, int col){
        this.heatloss = heatloss;
        this.row = row;
        this.col = col;
    }

    public void visit(Pair<Vector2, Integer> directionAndLength) {
        visitedDirectionsPerLength.add(directionAndLength);
    }

    public boolean isVisited(Pair<Vector2, Integer> directionAndLength) {
        return visitedDirectionsPerLength.contains(directionAndLength);
    }

    @Override
    public int compareTo(Tile other) {
        return Integer.compare(totalHeatloss, other.totalHeatloss);
    }

}
