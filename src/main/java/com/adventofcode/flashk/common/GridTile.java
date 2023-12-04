package com.adventofcode.flashk.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Identifies a single position on a {@link Grid},
 * containing information about both the <code>row</code> and <code>col</code>,
 * and the stored <code>value</code> at that position.
 * @param <T>
 */
@Getter
@AllArgsConstructor
public class GridTile<T> {

    private int row;
    private int col;

    @Setter
    private T value;

}
