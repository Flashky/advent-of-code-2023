package com.adventofcode.flashk.day04;

import lombok.Getter;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Getter
public class Card {

    private static final Pattern PATTERN_CARD_ID = Pattern.compile("Card *(\\d*):");
    private static final String CARD_ID_DELIMITER = ":";
    private static final String NUMBERS_DELIMITER = "\\|";
    private static final String WHITESPACES = "\\s+";

    private final int id;
    private final Set<Integer> matchingNumbers;

    public Card(String input) {

        // Card id parsing
        Matcher idMatcher = PATTERN_CARD_ID.matcher(input);
        id = idMatcher.find() ? Integer.parseInt(idMatcher.group(1)) : 0;

        // Card numbers parsing
        String numbersPart = input.substring(input.indexOf(CARD_ID_DELIMITER)+1);
        String[] numbersSplit = numbersPart.split(NUMBERS_DELIMITER);

        String[] winning = numbersSplit[0].stripLeading().split(WHITESPACES);
        String[] have = numbersSplit[1].stripLeading().split(WHITESPACES);

        Set<Integer> winningNumbers = Arrays.stream(winning).map(Integer::parseInt).collect(Collectors.toSet());
        Set<Integer> numbersYouHave = Arrays.stream(have).map(Integer::parseInt).collect(Collectors.toSet());
        matchingNumbers = numbersYouHave.stream().filter(winningNumbers::contains).collect(Collectors.toSet());
    }

    public int calculateWinningPoints() {
        return (int) Math.floor(Math.pow(2, (double) matchingNumbers.size()-1));
    }

}
