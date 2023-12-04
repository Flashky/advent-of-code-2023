package com.adventofcode.flashk.common;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ArrayGrid<T> implements Grid<T> {

    private final T[][] array;
    private final int rows;
    private final int cols;

    public ArrayGrid(T[][] array){
        this.array = array;
        this.rows = array.length;
        this.cols = array[0].length;
    }

    @Override
    public int rows() {
        return this.rows;
    }

    @Override
    public int cols() {
        return this.cols;
    }

    @Override
    public T get(int row, int col) {
        checkBounds(row, col);
        return array[row][col];
    }

    @Override
    public void set(int row, int col, T value) {
        checkBounds(row, col);
        array[row][col] = value;
    }

    @Override
    public Set<T> getAdjacentValues(int row, int col, boolean includeDiagonals) {
        checkBounds(row,col);

        // Initialize tiles
        Set<T> adjacentValues = new HashSet<>();
        getNullable(row, col-1).ifPresent(adjacentValues::add); // left
        getNullable(row, col+1).ifPresent(adjacentValues::add); // right
        getNullable(row-1, col).ifPresent(adjacentValues::add); // up
        getNullable(row+1, col).ifPresent(adjacentValues::add); // down

        if(includeDiagonals) {
            getNullable(row-1, col-1).ifPresent(adjacentValues::add); // up left
            getNullable(row-1, col+1).ifPresent(adjacentValues::add); // up right
            getNullable(row+1, col-1).ifPresent(adjacentValues::add); // down left
            getNullable(row+1, col+1).ifPresent(adjacentValues::add); // down right
        }

        return adjacentValues;
    }

    @Override
    public Set<GridTile<T>> getAdjacentTiles(int row, int col, boolean includeDiagonals) {
        checkBounds(row,col);

        // Initialize tiles
        Set<GridTile<T>> adjacentValues = new HashSet<>();
        getNullableGridTile(row, col-1).ifPresent(adjacentValues::add); // left
        getNullableGridTile(row, col+1).ifPresent(adjacentValues::add); // right
        getNullableGridTile(row-1, col).ifPresent(adjacentValues::add); // up
        getNullableGridTile(row+1, col).ifPresent(adjacentValues::add); // down

        if(includeDiagonals) {
            getNullableGridTile(row-1, col-1).ifPresent(adjacentValues::add); // up left
            getNullableGridTile(row-1, col+1).ifPresent(adjacentValues::add); // up right
            getNullableGridTile(row+1, col-1).ifPresent(adjacentValues::add); // down left
            getNullableGridTile(row+1, col+1).ifPresent(adjacentValues::add); // down right
        }

        return adjacentValues;
    }

    private void checkBounds(int row, int col) {
        if(rowIsOutOfBounds(row)) {
            String message = String.format("Row '%d' is out of array bounds: [0-%d]", row, rows-1);
            throw new IndexOutOfBoundsException(message);
        }

        if(colIsOutOfBounds(col)) {
            String message = String.format("Col '%d' is out of array bounds: [0-%d]",  col, cols-1);
            throw new IndexOutOfBoundsException(message);
        }
    }

    /**
     * Verifies if the row is out of bounds
     * @param row the row to check
     * @return <code>true</code> if <code>row</code> is out of bounds. <code>false</code> otherwise.
     */
    private boolean rowIsOutOfBounds(int row) {
        return row < 0 || row >= rows;
    }

    /**
     * Verifies if the col is out of bounds
     * @param col the col to check
     * @return <code>true</code> if <code>col</code> is out of bounds. <code>false</code> otherwise.
     */
    private boolean colIsOutOfBounds(int col) {
        return col < 0 || col >= cols;
    }

    /**
     * Retrieves the element at the specified row and col.
     * @param row row index of the element to return.
     * @param col col index of the element to return.
     * @return an {@link Optional} containing the row and col value. It will be {@link Optional#empty()} if row and cols are out of bounds
     */
    private Optional<T> getNullable(int row, int col) {
        if(!rowIsOutOfBounds(row) && (!colIsOutOfBounds(col))) {
            return Optional.of(array[row][col]);
        }
        return Optional.empty();
    }

    /**
     * Retrieves the {@link GridTile} element at the specified row and col.
     * @param row row index of the element to return.
     * @param col col index of the element to return.
     * @return an {@link Optional} containing the row and col tile information. Returns an {@link Optional#empty()} if row and col are out of bounds.
     */
    private Optional<GridTile<T>> getNullableGridTile(int row, int col) {
        if(!rowIsOutOfBounds(row) && (!colIsOutOfBounds(col))) {
            return Optional.of(new GridTile<>(row, col, array[row][col]));
        }
        return Optional.empty();
    }
}
