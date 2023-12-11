package com.adventofcode.flashk.day11;

import com.adventofcode.flashk.common.Vector2;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CosmicExpansionRefactor {

    private static final char SPACE = '.';
    private static final char GALAXY = '#';

    private char[][] map;
    private int rows;
    private int cols;

    private List<Vector2> galaxies = new ArrayList<>();
    private List<Integer> initialEmptyRows = new ArrayList<>();
    private List<Integer> initialEmptyCols = new ArrayList<>();

    public CosmicExpansionRefactor(char[][] map) {
        this.map = map;
        this.rows = map.length;
        this.cols = map[0].length;

        initializeGalaxies();
        initializeEmptyRows();
        initializeEmptyCols();
    }

    public long solve(int expansionRate) {
        int shiftUnits = expansionRate;

        // TODO Cuidado puede que desplacemos veces de más o de menos una coluimna
        // Expand the universe
        for(int col : initialEmptyCols) {
            galaxies = galaxies.stream().filter(g -> g.getX() > col).map(g -> moveRight(g, shiftUnits)).toList();
        }

        for(int row : initialEmptyRows) {
            galaxies = galaxies.stream().filter(g -> g.getY() > row).map(g -> moveDown(g, shiftUnits)).toList();
        }

        // Calculate distances
        return manhattanDistances();
    }

    private long manhattanDistances() {
        long result = 0;
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
        return result;
    }

    private void initializeGalaxies() {
        for(int row = 0; row < rows; row++) {
            for(int col = 0; col < cols; col++) {
                galaxies.add(new Vector2(col, row));
            }
        }
    }

    private Vector2 moveRight(Vector2 galaxy, int units) {
        galaxy.transformX(units);
        return galaxy;
    }

    private Vector2 moveDown(Vector2 galaxy, int units) {
        galaxy.transformY(units);
        return galaxy;
    }

    private void initializeEmptyRows() {
        for(int row = 0; row < rows; row++) {
            if(rowHasNoGalaxies(row)) {
                initialEmptyRows.add(row);
            }
        }
    }

    private void initializeEmptyCols() {
        for(int col = 0; col < cols; col++) {
            if(colHasNoGalaxies(col)) {
                initialEmptyCols.add(col);
            }
        }
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
