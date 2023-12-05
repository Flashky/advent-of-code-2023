package com.adventofcode.flashk.day04;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Scratchcards {

    private final List<Card> cards;
    private final Map<Integer, Long> cardTotals;

    public Scratchcards(List<String> input) {
        cards = input.stream().map(Card::new).toList();
        cardTotals = cards.stream().collect(Collectors.groupingBy(Card::getId, Collectors.counting()));
    }

    public int solveA() {
        return cards.stream().map(Card::calculateWinningPoints).reduce(0, Integer::sum);
    }

    public long solveB() {

        for (Card card : cards) {
            int size = card.getMatchingNumbers().size();
            long totalToIncrease = cardTotals.get(card.getId());
            IntStream.rangeClosed(card.getId() + 1, card.getId() + size)
                    .filter(cardTotals::containsKey)
                    .forEach(nextCardId -> updateTotals(nextCardId, totalToIncrease));
        }

        return cardTotals.values().stream().reduce(0L, Long::sum);
    }

    private void updateTotals(int cardId, long totalToIncrease) {
        cardTotals.put(cardId, cardTotals.get(cardId) + totalToIncrease);
    }

}