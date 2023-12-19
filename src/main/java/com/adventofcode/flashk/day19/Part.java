package com.adventofcode.flashk.day19;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@ToString
public class Part {

    private static final Pattern PART_PATTERN = Pattern.compile("\\{x=(\\d*),m=(\\d*),a=(\\d*),s=(\\d*)}");

    private int x;
    private int m;
    private int a;
    private int s;

    public Part(String input) {
        Matcher matcher = PART_PATTERN.matcher(input);
        if(matcher.find()) {
            x = Integer.parseInt(matcher.group(1));
            m = Integer.parseInt(matcher.group(2));
            a = Integer.parseInt(matcher.group(3));
            s = Integer.parseInt(matcher.group(4));
        }
    }

    public int getRating(char letter) {
        return switch (letter) {
            case 'x' -> x;
            case 'm' -> m;
            case 'a' -> a;
            case 's' -> s;
            default -> throw new IllegalStateException("Unexpected value: " + letter);
        };
    }

    public int value() {
        return x+m+a+s;
    }
}
