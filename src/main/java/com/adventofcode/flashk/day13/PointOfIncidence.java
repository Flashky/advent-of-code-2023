package com.adventofcode.flashk.day13;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PointOfIncidence {

    private static final char ASH = '.';
    private static final char ROCK = '#';
    private final Iterator<String> inputsIterator;

    private char[][] map;
    private int rows;
    private int cols;

    public PointOfIncidence(List<String> inputs) {
        inputsIterator = inputs.iterator();
    }

    public long solveA() {
        long verticalColsToTheLeft = 0;
        long horizontalRowsToAbove = 0;

        while(inputsIterator.hasNext()) {
            readMap();
            verticalColsToTheLeft += evaluateVerticalMirrors(-1);
            horizontalRowsToAbove += evaluateHorizontalMirrors(-1);
        }

        return verticalColsToTheLeft + 100 * horizontalRowsToAbove;
    }

    public long solveB() {
        long verticalColsToTheLeft = 0;
        long horizontalRowsToAbove = 0;



        while(inputsIterator.hasNext()) {
            readMap();

            // Exclusion values
            long excludeVerticalCol = evaluateVerticalMirrors(-1);
            long excludeHorizontalRow = evaluateHorizontalMirrors(-1);

            boolean nextMap = false;
            for(int row = 0; row < rows;row++) {

                if(nextMap) {
                    break;
                }

                for(int col = 0; col < cols;col++) {

                    // Flip value
                    map[row][col] = map[row][col] == ASH ? ROCK : ASH;

                    long possibleVerticalColsToTheLeft = evaluateVerticalMirrors(excludeVerticalCol-1L);
                    long possibleHorizontalRowsToAbove = evaluateHorizontalMirrors(excludeHorizontalRow-1L);

                    // Importante: "different reflection line"

                    if(possibleVerticalColsToTheLeft != 0 || possibleHorizontalRowsToAbove != 0) {
                        nextMap = true;
                        verticalColsToTheLeft += possibleVerticalColsToTheLeft;
                        horizontalRowsToAbove += possibleHorizontalRowsToAbove;
                        break;
                    }


                    // Backtrack flip
                    map[row][col] = map[row][col] == ASH ? ROCK : ASH;



                }
            }

        }

        return verticalColsToTheLeft + 100 * horizontalRowsToAbove;
    }

    private long evaluateVerticalMirrors(long iExclude) {

        int i1 = 0;
        int i2 = 1;
        boolean found = false;

        while(!found && i2 < cols) {
            if(i1 != iExclude) {
                found = isVerticalMirror(i1++, i2++);
            } else {
                //found = isVerticalMirror(i1+2,i2+2); // Skip mirror
                i1++;
                i2++;
            }
        }

        if(found) {
            return i1;
        }

        return 0;
    }

    private long evaluateHorizontalMirrors(long iExclude) {
        int i1 = 0;
        int i2 = 1;
        boolean found = false;

        while(!found && i2 < rows) {
            if(i1 != iExclude) {
                found = isHorizontalMirror(i1++, i2++);
            } else {
                i1++;
                i2++;
            }
        }

        if(found) {return i1;}
        return 0;
    }

    private boolean isVerticalMirror(int i1, int i2) {

        if(i1 < 0 || i2 >= cols) {
            return true;
        }
        for(int row = 0; row < rows; row++) {
            if(map[row][i1] != map[row][i2]) {
                return false;
            }
        }
        
        return isVerticalMirror(i1-1, i2+1);
    }

    private boolean isHorizontalMirror(int i1, int i2) {

        if(i1 < 0 || i2 >= rows) {
            return true;
        }

        String row1 = new String(map[i1]);
        String row2 = new String(map[i2]);

        if(!row1.equals(row2)) {
            return false;
        }

        return isHorizontalMirror(i1-1, i2+1);
    }

    private void readMap() {

        List<String> mapRows = new ArrayList<>();
        boolean emptyRow = false;
        while(inputsIterator.hasNext() && !emptyRow) {
            String row = inputsIterator.next();
            if(!StringUtils.EMPTY.equals(row)) {
                mapRows.add(row);
            } else {
                emptyRow = true;
            }
        }

        initializeMap(mapRows);

    }

    private void initializeMap(List<String> mapRows) {
        rows = mapRows.size();
        cols = mapRows.get(0).length();
        map = new char[rows][cols];

        int i = 0;
        for(String row : mapRows) {
            map[i++] = row.toCharArray();
        }
    }

}
