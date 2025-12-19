package com.adventofcode.flashk.day23;

import module java.base;
import com.adventofcode.flashk.common.Vector2;

public class ALongWalkBacktrack {

    private final Set<Vector2> directions = Set.of(Vector2.up(), Vector2.left(),
            Vector2.right(), Vector2.down());

    private static final char WALL = '#';

    private final char[][] map;
    private final int rows;
    private final int cols;
    private final Vector2 start;
    private final Vector2 end;

    public ALongWalkBacktrack(char[][] inputs) {
        rows = inputs.length;
        cols = inputs[0].length;
        map = inputs;

        start = new Vector2(1, 0);
        end = new Vector2(cols-2, rows-1);
    }

    public long solveB() {

        // Set initial visited as position, also start is not counted for the solution
        Set<Vector2> visited = new HashSet<>();
        visited.add(start);

        return dfs(start, visited, 0);

    }

    private long dfs(Vector2 pos, Set<Vector2> visited, long steps) {
        if(end.equals(pos)) {
            return steps;
        }

        long result = 0;
        Set<Vector2> adjacents = getAdjacents(pos);

        for(Vector2 adjacentPos : adjacents) {
            if(!visited.contains(adjacentPos)) {

                visited.add(adjacentPos);

                long currentResult = dfs(adjacentPos, visited, steps+1);
                result = Math.max(currentResult, result);

                // Backtrack
                visited.remove(adjacentPos);
            }
        }
        return result;
    }

    private Set<Vector2> getAdjacents(Vector2 pos) {
        Set<Vector2> adjacentTiles = new HashSet<>();

        for(Vector2 direction : directions) {
            Vector2 newPos = Vector2.transform(pos, direction);
            if(isInbounds(newPos) && isNotWall(newPos)) {
                adjacentTiles.add(newPos);
            }
        }

        return adjacentTiles;
    }

    private boolean isInbounds(Vector2 pos) {
        return (pos.getY() >= 0 && pos.getY() < rows && pos.getX() >= 0 && pos.getX() < cols);
    }

    private boolean isNotWall(Vector2 pos) {
        return map[pos.getY()][pos.getX()] != WALL;
    }
}
