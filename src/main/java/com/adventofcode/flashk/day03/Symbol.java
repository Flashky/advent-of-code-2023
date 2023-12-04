package com.adventofcode.flashk.day03;

import lombok.Getter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Symbol {

    private static final char GEAR = '*';

    private final char value;
    private final int row;
    private final int col;

    private final List<Integer> adjacentPartNumbers = new ArrayList<>();

    public Symbol(int row, int col, char value) {
        this.row = row;
        this.col = col;
        this.value = value;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Symbol symbol = (Symbol) o;

        return new EqualsBuilder().append(row, symbol.row).append(col, symbol.col).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(row).append(col).toHashCode();
    }
}
