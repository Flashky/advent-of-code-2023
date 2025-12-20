package com.adventofcode.flashk.day17;


import module java.base;
import com.adventofcode.flashk.common.Vector2;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
public class Tile implements Comparable<Tile>  {

    private final Vector2 pos;
    private final long heatloss;

    @Setter
    private long totalHeatloss = Long.MAX_VALUE;

    public Tile(int heatloss, int row, int col){
        this.heatloss = heatloss;
        this.pos = new Vector2(col, row);
    }

    @Override
    public int compareTo(@NotNull Tile other) {
        return Long.compare(totalHeatloss, other.totalHeatloss);
    }
}
