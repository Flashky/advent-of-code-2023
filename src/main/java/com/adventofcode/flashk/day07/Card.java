package com.adventofcode.flashk.day07;

import lombok.Getter;

@Getter
public class Card implements Comparable<Card> {

    private final char number;
    private final int value;

    public Card(char number, boolean useJokers) {
        this.number = number;
        switch(number) {
            case 'A': value = 14; break;
            case 'K': value = 13; break;
            case 'Q': value = 12; break;
            case 'J': value = useJokers ? 1 : 11; break; // Jokers are the weakest in part 2
            case 'T': value = 10; break;
            default: value = Character.getNumericValue(number);
        }
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(this.value, o.value);
    }
}
