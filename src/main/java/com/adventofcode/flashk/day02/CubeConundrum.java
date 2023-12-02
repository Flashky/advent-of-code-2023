package com.adventofcode.flashk.day02;

import java.util.List;

public class CubeConundrum {

    private final List<Game> games;

    public CubeConundrum(List<String> inputs) {
        games = inputs.stream().map(Game::new).toList();
    }

    public int solveA(int redCubes, int greenCubes, int blueCubes) {
        return games.stream()
                .filter(g -> g.getMaxRedCubes() <= redCubes)
                .filter(g -> g.getMaxGreenCubes() <= greenCubes)
                .filter(g -> g.getMaxBlueCubes() <= blueCubes)
                .map(Game::getId)
                .reduce(0, Integer::sum);
    }

    public int solveB() {
        return games.stream().map(Game::getPower).reduce(0, Integer::sum);
    }
}