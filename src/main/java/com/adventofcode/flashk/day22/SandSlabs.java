package com.adventofcode.flashk.day22;

import module java.base;
import com.adventofcode.flashk.common.Vector3;

public class SandSlabs {

    private static final Vector3 UP = Vector3.up();
    private static final Vector3 DOWN = Vector3.down();

    private final List<Brick> bricks;

    public SandSlabs(List<String> inputs) {
        bricks = inputs.stream().map(Brick::new).collect(Collectors.toList());
    }

    public long solveA() {
        move();
        return disintegrateSafe();
    }

    public long solveB() {
        move();
        commit();
        return disintegrateChain();
    }

    /// Counts the number of bricks that can be disintegrated safely.
    /// @return the number of bricks that can be dise
    private long disintegrateSafe() {
        long count = 0;

        List<Brick> bricksSnapshot = new ArrayList<>(bricks);

        for(Brick brickToRemove : bricksSnapshot) {
            bricks.remove(brickToRemove);
            if(!bricksCanFall()) {
                count++;
            }
            bricks.add(brickToRemove);
        }

        return count;
    }


    public long disintegrateChain() {
        long count = 0;

        List<Brick> bricksSnapshot = new ArrayList<>(bricks);

        for(Brick brickToRemove : bricksSnapshot){
            bricks.remove(brickToRemove);
            count += move();
            bricks.add(brickToRemove);
            reset();
        }

        return count;
    }

    /// Simulates the sand slabs fall to the ground.
    /// @return the number of bricks that fell down.
    private long move() {

        Set<Brick> movedBricks = new HashSet<>();
        boolean hasMoved;
        do {
            hasMoved = false;
            for(Brick brick: bricks) {
                brick.move(DOWN);
                if(collidesWithAnything(brick)) {
                    brick.move(UP); // Restore original position
                } else {
                    movedBricks.add(brick);
                    hasMoved = true;
                }
            }
        } while(hasMoved);

        return movedBricks.size();

    }

    /// Checks if the brick collides with any other brick
    /// @return `true` if brick collides with at least another brick.
    private boolean collidesWithAnything(Brick brick) {

        // Is below minimum allowed position (ground is at z = 0)
        if(isBelowGround(brick)) {
            return true;
        }

        for(Brick otherBrick : bricks) {
            if(brick.collidesWith(otherBrick)) {
                return true;
            }
        }

        return false;
    }

    /// Applies up movement (rise) movement to all bricks that can move
    private boolean bricksCanFall() {

        for(Brick brick : bricks) {
            brick.move(DOWN);
            try {
                if(!collidesWithAnything(brick)) {
                    return true;
                }
            } finally {
                brick.move(UP);
            }
        }

        return false;
    }

    private boolean isBelowGround(Brick brick) {
        return brick.getMinZ() < 1;
    }

    private void reset() {
        bricks.forEach(Brick::reset);
    }

    private void commit() {
        bricks.forEach(Brick::commit);
    }

}
