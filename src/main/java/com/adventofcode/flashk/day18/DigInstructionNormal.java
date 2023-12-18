package com.adventofcode.flashk.day18;

import com.adventofcode.flashk.common.Vector2Long;

import java.util.regex.Matcher;
public class DigInstructionNormal extends DigInstruction {

    public DigInstructionNormal(String input) {
        super();
        Matcher matcher = DIG_PATTERN.matcher(input);
        if(matcher.find()){

            // Direction
            switch (matcher.group(1)) {
                case "U" -> direction = Vector2Long.up();
                case "D" -> direction = Vector2Long.down();
                case "R" -> direction = Vector2Long.right();
                case "L" -> direction = Vector2Long.left();
                default -> throw new UnsupportedOperationException("Invalid direction");
            }

            // Distance
            distance = Integer.parseInt(matcher.group(2));
        }
    }


}
