package com.adventofcode.flashk.day11;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CosmicExpansion {

    private static final char SPACE = '.';
    private static final char GALAXY = '#';

    private char[][] map;
    private int rows;
    private int cols;

    public CosmicExpansion(char[][] map) {
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
    }

    public long solveA() {

        List<Integer> emptyCols = getEmptyCols();
        List<Integer> emptyRows = getEmptyRows();

        expandColumns(emptyCols);
        expandRows(emptyRows);

        long result = 0;
        return result;
    }

    private void expandColumns(List<Integer> emptyCols) {

        int shift = 0;
        int newCols = cols;
        for(int col : emptyCols) {

            col += shift;
            newCols++;
            char[][] newMap = new char[rows][newCols];

            // Relleno el nuevo mapa
            for(int i = 0; i < rows; i++) {

                // Initialize row
                newMap[i] = new char[newCols];

                // First half
                for(int j = 0; j < col; j++) {
                    newMap[i][j] = map[i][j];
                }

                // New column
                newMap[i][col] = SPACE;

                // Second half
                for(int j = col; j < cols; j++) {
                    newMap[i][j+1] = map[i][j];
                }

            }

            map = newMap;
            rows = map.length;
            cols = map[0].length;
            shift++; // Anytime a column is expanded this counter needs to shift

        }

    }

    private void expandRows(List<Integer> emptyRows) {

        int shift = 0;
        int newRows = rows;
        for(int row : emptyRows) {

            newRows++;
            row += shift;
            char[][] newMap = new char[newRows][cols];

            // First half of rows
            for(int i = 0; i < row; i++) {
                newMap[i] = map[i];
            }

            // New empty row
            newMap[row] = StringUtils.repeat(SPACE, cols).toCharArray();

            // Second half of rows
            for(int i = row; i < rows; i++) {
                newMap[i+1] = map[i];
            }

            map = newMap;
            rows = map.length;
            cols = map[0].length;
            shift++; // Anytime a row is expanded this counter needs to shift

        }

    }

    private List<Integer> getEmptyRows() {
        List<Integer> emptyRows = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            if(rowHasNoGalaxies(row)) {
                emptyRows.add(row);
            }
        }
        return emptyRows;
    }

    private List<Integer> getEmptyCols() {
        List<Integer> emptyCols = new ArrayList<>();
        for(int col = 0; col < cols; col++) {
            if(colHasNoGalaxies(col)) {
                emptyCols.add(col);
            }
        }

        return emptyCols;
    }
    private boolean rowHasNoGalaxies(int row){
        String values = new String(map[row]);
        return values.length() == StringUtils.countMatches(values, SPACE);
    }

    private boolean colHasNoGalaxies(int col){
        for(int row = 0; row < rows; row++) {
            if(map[row][col] != SPACE) {
                return false;
            }
        }
        return true;
    }
}
