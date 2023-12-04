package com.adventofcode.flashk.common;

import java.util.ArrayList;
import java.util.List;

public class GridUtil {

    private GridUtil() {}

    public static List<Vector2> getAdjacentsIncludingDiagonals(char[][] array, int row, int col) {
        int maxRows = array.length;
        int maxCols = array[0].length;
        return getVector2s(row, col, maxCols, maxRows, true);
    }

    public static List<Vector2> getAdjacents(char[][] array, int row, int col) {
        int maxRows = array.length;
        int maxCols = array[0].length;
        return getVector2s(row, col, maxCols, maxRows, false);
    }

    public static List<Vector2> getAdjacentsIncludingDiagonals(int[][] array, int row, int col) {
        int maxRows = array.length;
        int maxCols = array[0].length;
        return getVector2s(row, col, maxCols, maxRows, true);
    }

    public static List<Vector2> getAdjacents(int[][] array, int row, int col) {
        int maxRows = array.length;
        int maxCols = array[0].length;
        return getVector2s(row, col, maxCols, maxRows, false);
    }

    private static List<Vector2> getVector2s(int row, int col, int maxCols, int maxRows, boolean includeDiagonals) {

        // Initialize vector
        Vector2 start = new Vector2(col, row);

        List<Vector2> tiles = new ArrayList<>();
        tiles.add(Vector2.substract(start, Vector2.left()));
        tiles.add(Vector2.substract(start, Vector2.right()));
        tiles.add(Vector2.substract(start, Vector2.up()));
        tiles.add(Vector2.substract(start, Vector2.down()));

        if(includeDiagonals) {
            tiles.add(Vector2.substract(start, Vector2.upLeft()));
            tiles.add(Vector2.substract(start, Vector2.upRight()));
            tiles.add(Vector2.substract(start, Vector2.downLeft()));
            tiles.add(Vector2.substract(start, Vector2.downRight()));
        }

        return tiles.stream()
                .filter(v -> v.getX() >= 0)
                .filter(v -> v.getX() < maxCols)
                .filter(v -> v.getY() >= 0)
                .filter(v -> v.getY() < maxRows)
                .toList();
    }

    public static Grid<Character> asGrid(char[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        Character[][] characterArray = new Character[rows][cols];

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                characterArray[row][col] = array[row][col];
            }
        }

        return new ArrayGrid<>(characterArray);
    }

    public static Grid<Integer> asGrid(int[][] array) {
        int rows = array.length;
        int cols = array[0].length;
        Integer[][] integerArray = new Integer[rows][cols];

        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                integerArray[row][col] = array[row][col];
            }
        }

        return new ArrayGrid<>(integerArray);
    }
}
