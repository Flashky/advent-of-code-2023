package com.adventofcode.flashk.common;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Vector2Long {

    private long x;
    private long y;

    public Vector2Long(Vector2Long anotherVector) {
        this.x = anotherVector.x;
        this.y = anotherVector.y;
    }

    public void multiply(long scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public static Vector2Long transform(Vector2Long start, Vector2Long end) {

        long x = start.x + end.x;
        long y = start.y + end.y;

        return new Vector2Long(x,y);
    }

    /**
     * Shorthand for <code>Vector2(1,0)</code>.
     * @return A unitary vector that points to the right.
     */
    public static Vector2Long right() {
        return new Vector2Long(1,0);
    }

    /**
     * Shorthand for <code>Vector2(-1,0)</code>.
     * @return A unitary vector that points to the left.
     */
    public static Vector2Long left() {
        return new Vector2Long(-1,0);
    }

    /**
     * Shorthand for <code>Vector2(0,1)</code>.
     * @return A unitary vector that points up.
     */
    public static Vector2Long up() {
        return new Vector2Long(0,1);
    }

    /**
     * Shorthand for <code>Vector2(0,-1)</code>.
     * @return A unitary vector that points down.
     */
    public static Vector2Long down() {
        return new Vector2Long(0,-1);
    }

}
