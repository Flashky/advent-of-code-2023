package com.adventofcode.flashk.day18;

import com.adventofcode.flashk.common.Vector2Long;

import java.util.regex.Matcher;

public class DigInstructionHex extends DigInstruction {

    public DigInstructionHex(String input) {
        super();
        Matcher matcher = DIG_PATTERN.matcher(input);
        if(matcher.find()){

            String color = matcher.group(3);

            // Direction
            switch(color.charAt(6)) {
                case '0' -> direction = Vector2Long.right();
                case '1' -> direction = Vector2Long.down();
                case '2' -> direction = Vector2Long.left();
                case '3' -> direction = Vector2Long.up();
                default -> throw new UnsupportedOperationException("Invalid direction");
            }

            // Distance
            String distanceHex = color.substring(1, 6);
            distance = Integer.parseInt(distanceHex, 16);

        }
    }
}
