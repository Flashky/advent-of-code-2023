package com.adventofcode.flashk.day02;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.Getter;

@Getter
public class Game {

    private final static Pattern PATTERN = Pattern.compile("Game (\\d+)");
    private final static String TURN_DELIMITER = ";";

    private final int id;
    private final int maxRedCubes;
    private final int maxGreenCubes;
    private final int maxBlueCubes;
    private final int power;

    public Game(String input) {

        // Game id
        Matcher matcher = PATTERN.matcher(input);
        id = matcher.find() ? Integer.parseInt(matcher.group(1)) : 0;

        // Game turns
        String[] inputTurns = input.split(TURN_DELIMITER);
        List<GameTurn> turns = Arrays.stream(inputTurns).map(GameTurn::new).toList();

        // Game calculations
        maxRedCubes = turns.stream().map(GameTurn::getRedCubes).max(Integer::compareTo).orElse(0);
        maxGreenCubes = turns.stream().map(GameTurn::getGreenCubes).max(Integer::compareTo).orElse(0);
        maxBlueCubes = turns.stream().map(GameTurn::getBlueCubes).max(Integer::compareTo).orElse(0);
        power = maxRedCubes * maxGreenCubes * maxBlueCubes;

    }

}