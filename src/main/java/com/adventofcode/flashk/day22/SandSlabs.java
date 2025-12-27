package com.adventofcode.flashk.day22;

import static java.lang.IO.println;

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
        return desintegrate();
    }

    public long solveB() {
        move();
        long result = desintegrateB();
        return result;
    }
    /// Simulates the sand slabs fall to the ground
    private void move() {
        boolean hasMoved;
        do {
            hasMoved = moveBricks();
        } while(hasMoved);
    }

    /// Applies down movement (fall) movement to all bricks that can fall.

    /// @return `true` if at least one brick has moved. `false` otherwise.
    private boolean moveBricks() {
        boolean hasMoved = false;
        for(Brick brick : bricks) {
            if(!brick.isAtGround()) {
                brick.move(DOWN);
                if(collidesWithAnything(brick)) {
                    brick.move(UP); // Restore original position
                } else {
                    hasMoved = true;
                }
            }
        }
        return hasMoved;
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

    private long desintegrate() {
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

    private long moveBricksAndCount() {
        long movedBricks = 0;
        for(Brick brick : bricks) {
            if(!brick.isAtGround()) {
                brick.move(DOWN);
                if(collidesWithAnything(brick)) {
                    brick.move(UP); // Restore original position
                } else {
                    movedBricks++;
                }
            }
        }
        return movedBricks;
    }

    public long desintegrateB() {
        return 0;
    }

    /// Checks if the brick collides with any other brick
    /// @return `true` if brick collides with at least another brick.
    private boolean collidesWithAnything(Brick brick) {

        if(brick.isAtGround()) {
            return true;
        }

        for(Brick otherBrick : bricks) {
            if(brick.collidesWith(otherBrick)) {
                return true;
            }
        }

        return false;
    }

}
