package com.adventofcode.flashk.day24;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NeverTellMeTheOdds {

    private List<HailstoneRefactor> hailstones;
    public NeverTellMeTheOdds(List<String> inputs, boolean isPartOne) {

        if(isPartOne) {
            hailstones = inputs.stream().map(s -> new HailstoneRefactor(s, true)).toList();
        } else {
            hailstones = inputs.stream().map(s -> new HailstoneRefactor(s, false)).toList();
        }

        int a = 3;


    }

    public long solveA(long min, long max) {

        // Compare all hailstones combinations
        long result = 0;

        List<HailstoneRefactor> hailstonesCopy = new ArrayList<>(hailstones);

        while(!hailstonesCopy.isEmpty()) {
            Iterator<HailstoneRefactor> hailstoneIterator = hailstonesCopy.iterator();
            HailstoneRefactor hailstoneA = hailstoneIterator.next();
            while(hailstoneIterator.hasNext()) {
                HailstoneRefactor hailstoneB = hailstoneIterator.next();
                if(hailstoneA.intersectsInFuture(hailstoneB, min, max)) {
                    result++;
                }
            }
            hailstonesCopy.remove(0);
        }

        return result;
    }

}
