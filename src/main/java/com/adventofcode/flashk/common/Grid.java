package com.adventofcode.flashk.common;

import java.util.Set;

public interface Grid<T> {

    /**
     * Returns the number of rows of the grid.
     * @return the number of rows
     */
    int rows();

    /**
     * Returns the number of cols of the grid.
     * @return the number of cols
     */
    int cols();

    /**
     * Returns the element specified at the specified row and col position.
     * @param row row index of the element to return.
     * @param col col index of the element to return
     * @return the element at the specified row and col indexes.
     * @throws IndexOutOfBoundsException if the row or col indexes are less than 0 or greater than the grid dimensions.
     */
    T get(int row, int col);

    /**
     * Inserts the specified element at the specified row and col position.
     * @param row row index to insert the element at.
     * @param col col index to insert the element at.
     * @param value the element to insert.
     * @throws IndexOutOfBoundsException if the row or col indexes are less than 0 or greater than the grid dimensions.
     */
    void set(int row, int col, T value);

    /**
     * Obtains the adjacent elements next to the specified row and col position.
     * @param row row index of the element to search the adjacents.
     * @param col col index of the element to search the adjacents.
     * @param includeDiagonals set to <code>true</code> if diagonal positions must be included or <code>false</code> otherwise.
     * @return a set of element values that are adjacent to the specified position.
     * @throws IndexOutOfBoundsException if the row or col indexes are less than 0 or greater than the grid dimensions.
     */
    Set<T> getAdjacentValues(int row, int col, boolean includeDiagonals);

    /**
     * Obtain the adjacent elements information next to the specified row and col position.
     * @param row row index of the element to search the adjacents.
     * @param col col index of the element to search the adjacents.
     * @param includeDiagonals set to <code>true</code> if diagonal positions must be included or <code>false</code> otherwise.
     * @return a set of {@link GridTile} that contains information of both the element and its position.
     * @throws IndexOutOfBoundsException if the row or col indexes are less than 0 or greater than the grid dimensions.
     * @see GridTile
     */
    Set<GridTile<T>> getAdjacentTiles(int row, int col, boolean includeDiagonals);
}
