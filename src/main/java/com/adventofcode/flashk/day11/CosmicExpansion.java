package com.adventofcode.flashk.day11;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CosmicExpansion {

    private static final long PART_2_EXPANSION_RATE = 1000000;

    private static final char SPACE = '.';
    private static final char GALAXY = '#';

    private final char[][] originalMap;
    private char[][] map;
    private int rows;
    private int cols;

    public CosmicExpansion(char[][] map) {
        this.originalMap = map;
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;
    }

    public long solveA(int expansionRate) {

        long result = 0;

        expandColumns(expansionRate);
        expandRows(expansionRate);

        // Search for galaxies now
        List<Vector2> galaxies = findGalaxies();

        Iterator<Vector2> galaxyIterator = galaxies.iterator();
        int nextGalaxyIndex = 0;
        while(galaxyIterator.hasNext()) {
            Vector2 galaxy = galaxyIterator.next();
            nextGalaxyIndex++;
            for(int i = nextGalaxyIndex; i < galaxies.size(); i++) {
                Vector2 otherGalaxy = galaxies.get(i);
                result += Vector2.manhattanDistance(galaxy, otherGalaxy);
            }
        }
        resetMap();
        return result;
    }

    public long solveB(){

        // Line equation:
        // y = mx + b
        // So given x = 1000000
        // Calculate m (slope) and b (intercept) and then substitute

        int x1 = 2;
        int x2 = 10;
        long y1 = solveA(x1);
        long y2 = solveA(x2);

        SimpleRegression regression = new SimpleRegression();
        regression.addData(x1, y1);
        regression.addData(x2, y2);

        long m = (long) regression.getSlope();
        long b = (long) regression.getIntercept();

        return m * PART_2_EXPANSION_RATE + b;

    }

    private List<Vector2> findGalaxies() {
        List<Vector2> galaxies = new ArrayList<>();
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                if(map[row][col] == GALAXY) {
                    galaxies.add(new Vector2(col, row));
                }
            }
        }

        return galaxies;
    }

    private void expandColumns(int expansionRate) {

        List<Integer> emptyCols = getEmptyCols();

        int shift = 0;
        int newCols = cols;
        int duplicatedCols = expansionRate - 1;
        for(int col : emptyCols) {

            col += shift;
            newCols += duplicatedCols;
            char[][] newMap = new char[rows][newCols];

            // Relleno el nuevo mapa
            for(int i = 0; i < rows; i++) {

                // Initialize row
                newMap[i] = new char[newCols];

                // First half - Copy from 0 to col (exclusive) into newMap
                System.arraycopy(map[i], 0, newMap[i], 0, col);

                // Fill expansion columns
                for(int times = 0; times < duplicatedCols; times++) {
                    newMap[i][col+times] = SPACE;
                }

                // Second half - Copy from col + duplicatedCols to the end (exclusive) into newMap
                System.arraycopy(map[i], col, newMap[i], col + duplicatedCols, cols - col);


            }

            map = newMap;
            rows = map.length;
            cols = map[0].length;
            shift += duplicatedCols; // Anytime a column is expanded this counter needs to shift

        }

    }

    private void expandRows(int expansionRate) {

        List<Integer> emptyRows = getEmptyRows();

        int shift = 0;
        int newRows = rows;
        int duplicatedRows = expansionRate - 1;
        for(int row : emptyRows) {

            newRows += duplicatedRows;
            row += shift;
            char[][] newMap = new char[newRows][cols];

            // First half - Copy from 0 to row (exclusive) into newMap
            System.arraycopy(map, 0, newMap, 0, row);

            // Fill expansion rows
            for(int times = 0; times < duplicatedRows; times++) {
                newMap[row+times] = StringUtils.repeat(SPACE, cols).toCharArray();
            }

            // Second half - Copy from row + duplicatedRows to the end (exclusive) into newMap
            System.arraycopy(map, row, newMap, row + duplicatedRows, rows - row);

            map = newMap;
            rows = map.length;
            cols = map[0].length;
            shift += duplicatedRows; // Anytime a row is expanded this counter needs to shift

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

    private void resetMap() {
        this.map = originalMap;
        this.rows = this.map.length;
        this.cols = this.map[0].length;
    }
}
