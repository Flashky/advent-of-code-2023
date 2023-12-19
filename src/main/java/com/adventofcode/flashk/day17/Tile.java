package com.adventofcode.flashk.day17;

import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class Tile implements Comparable<Tile>{

    private static final Vector2 REVERSE = new Vector2(-1,-1);
    public static final Vector2 RIGHT = Vector2.right();
    public static final Vector2 LEFT = Vector2.left();
    public static final Vector2 UP = Vector2.down();
    public static final Vector2 DOWN = Vector2.up();

    private int row;
    private int col;
    private int totalHeatloss = Integer.MAX_VALUE;
    private int heatloss;

    //private Tile parent;
    private Vector2 travelDirection;
    private int travelLength;

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

    public Vector2 getPosition() {
        return new Vector2(col,row);
    }

    public List<Pair<Vector2,Integer>> nextDirections(Vector2 currentDirection, int currentLength) {
        List<Pair<Vector2,Integer>> candidateDirections = new ArrayList<>();

        Vector2 reversedDirection = new Vector2(currentDirection);
        reversedDirection.multiply(-1);


        Vector2 nextDirection = Vector2.right();
        int nextLength = nextLength(nextDirection, currentDirection, currentLength);
        if(!nextDirection.equals(reversedDirection) && nextLength < 4) {
            candidateDirections.add(ImmutablePair.of(nextDirection, nextLength));
        }

        nextDirection = Vector2.left();
        nextLength = nextLength(nextDirection, currentDirection, currentLength);
        if(!nextDirection.equals(reversedDirection) && nextLength < 4) {
                candidateDirections.add(ImmutablePair.of(nextDirection, nextLength));
        }

        nextDirection = Vector2.down();
        nextLength = nextLength(nextDirection, currentDirection, currentLength);
        if(!nextDirection.equals(reversedDirection) && nextLength < 4) {
            candidateDirections.add(ImmutablePair.of(nextDirection, nextLength));
        }

        nextDirection = Vector2.up();
        nextLength = nextLength(nextDirection, currentDirection, currentLength);
        if(!nextDirection.equals(reversedDirection) && nextLength < 4) {
            candidateDirections.add(ImmutablePair.of(nextDirection, nextLength));
        }

        return candidateDirections;
    }

    private int nextLength(Vector2 direction, Vector2 currentDirection, int currentLength) {

        if(direction.equals(currentDirection)) {
            return currentLength + 1;
        }

        return 1; // TODO o currentLength? si la dirección cambia cambiará la longitud

    }

    @Override
    public int compareTo(Tile other) {
        return Integer.compare(totalHeatloss, other.totalHeatloss);
    }

}
