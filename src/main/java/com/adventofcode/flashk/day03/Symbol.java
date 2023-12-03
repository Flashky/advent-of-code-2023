package com.adventofcode.flashk.day03;

import com.adventofcode.flashk.common.Vector2;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode
public class Symbol {

    private static final char GEAR = '*';

    private final char value;

    @Getter
    private final Vector2 pos;

    private final List<Integer> adjacentPartNumbers = new ArrayList<>();

    public Symbol(char value, Vector2 pos) {
        this.value = value;
        this.pos = pos;
    }

    public void addAdjacentPartNumber(int partNumber) {
        this.adjacentPartNumbers.add(partNumber);
    }

    public boolean isGear() {
        return value == GEAR;
    }

    public int sumPartNumbers() {
        return adjacentPartNumbers.stream().reduce(0, Integer::sum);
    }

    public int gearRatio() {
        if(isGear() && adjacentPartNumbers.size() == 2) {
            return adjacentPartNumbers.get(0) * adjacentPartNumbers.get(1);
        }

        return 0;
    }

}
