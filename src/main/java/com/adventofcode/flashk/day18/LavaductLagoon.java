package com.adventofcode.flashk.day18;

import com.adventofcode.flashk.common.Vector2Long;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LavaductLagoon {

    private final List<String> inputs;

    public LavaductLagoon(List<String> inputs) {
        this.inputs = inputs;
    }

    public long solveA() {
        List<DigInstruction> instructions = inputs.stream().map(DigInstructionNormal::new).collect(Collectors.toList());
        return solve(instructions);
    }

    public long solveB() {
        List<DigInstruction> instructions = inputs.stream().map(DigInstructionHex::new).collect(Collectors.toList());
        return solve(instructions);
    }

    private long solve(List<DigInstruction> instructions) {
        long area = area(instructions);
        long perimeter = perimeter(instructions);
        long internalVertex = area - (perimeter / 2) + 1;

        return perimeter + internalVertex;
    }

    private long area(List<DigInstruction> instructions) {
        Deque<Vector2Long> polygonPoints = new ArrayDeque<>();
        polygonPoints.addFirst(new Vector2Long(0,0));

        for(DigInstruction instruction : instructions) {
            Vector2Long end = instruction.dig(polygonPoints.peekLast());
            polygonPoints.addLast(end);
        }

        Iterator<Vector2Long> pointIterator = polygonPoints.iterator();
        Vector2Long p1 = pointIterator.next();
        long xPerY = 0;
        long yPerX = 0;
        while(pointIterator.hasNext()) {
            Vector2Long p2  = pointIterator.next();
            xPerY += (p1.getX() * p2.getY());
            yPerX += (p1.getY() * p2.getX());
            p1 = p2;

        }

        return Math.abs(xPerY - yPerX) / 2;
    }

    private long perimeter(List<DigInstruction> instructions) {
        return instructions.stream().map(DigInstruction::getDistance).reduce(0L, Long::sum);
    }
}
