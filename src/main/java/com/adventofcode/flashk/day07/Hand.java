package com.adventofcode.flashk.day07;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Hand implements Comparable<Hand> {

    private static final Pattern HANDS_PATTERN = Pattern.compile("(\\w*) (\\d*)");
    private static final Character JOKER = 'J';

    private List<Card> cards;

    private int bid;
    private HandType handType; // The hand type from strongest (7) to weakest (1).

    public Hand(String input, boolean useJokers) {
        Matcher handMatcher = HANDS_PATTERN.matcher(input);

        if(handMatcher.find()) {
            cards = handMatcher.group(1).chars().mapToObj(c -> new Card((char)c, useJokers)).toList();
            bid = Integer.parseInt(handMatcher.group(2));
        }

        setHandType(useJokers);
    }

    private void setHandType(boolean useJokers) {
        Map<Character, Long> cardsByType = cards.stream()
                .map(Card::getNumber)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        if((!useJokers) || (!cardsByType.containsKey(JOKER))){
            setHandType(cardsByType);
        } else {
            setHandTypeWithJokers(cardsByType);
        }
    }

    private void setHandType(Map<Character,Long> cardsByType) {
        handType = switch(cardsByType.size()) {
            case 1: yield HandType.FIVE_OF_A_KIND;
            case 2: yield cardsByType.containsValue(4L) ? HandType.FOUR_OF_A_KIND : HandType.FULL_HOUSE;
            case 3: yield cardsByType.containsValue(3L) ? HandType.THREE_OF_A_KIND : HandType.TWO_PAIR;
            case 4: yield HandType.ONE_PAIR;
            case 5: yield HandType.HIGH_CARD;
            default: throw new IllegalStateException("Unexpected value: " + cardsByType.size());
        };
    }

    private void setHandTypeWithJokers(Map<Character,Long> cardsByType) {

        handType = switch(cardsByType.size()) {
            case 1,2: yield HandType.FIVE_OF_A_KIND;
            case 3: yield (cardsByType.containsValue(3L) || (cardsByType.get(JOKER) == 2L)) ? HandType.FOUR_OF_A_KIND : HandType.FULL_HOUSE;
            case 4: yield HandType.THREE_OF_A_KIND;
            case 5: yield HandType.ONE_PAIR;
            default: throw new IllegalStateException("Unexpected value: " + cardsByType.size());
        };
    }

    public int getValue(int rank) {
        return bid * rank;
    }

    @Override
    public int compareTo(Hand o) {

        // Wins highest hand
        if(this.handType != o.handType) {
            return Integer.compare(this.handType.getValue(), o.handType.getValue());
        }

        // Wins first highest card found from left to right
        int i = 0;
        while(i < cards.size()) {
            Card thisCard = cards.get(i);
            Card otherCard = o.cards.get(i);
            if(thisCard.compareTo(otherCard) != 0) {
                return thisCard.compareTo(otherCard);
            }
            i++;
        }

        return 0;
    }
}
