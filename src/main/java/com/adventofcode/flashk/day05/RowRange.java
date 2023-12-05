package com.adventofcode.flashk.day05;

import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class RowRange {

    private static final Pattern RANGE_PATTERN = Pattern.compile("^(\\d*) (\\d*) (\\d*)$");

    private long originStart;
    private long originEnd;

    private long destinationStart;
    private long destinationEnd;

    private long length;

    private long offset;

    public RowRange(String input) {

        Matcher matcher = RANGE_PATTERN.matcher(input);
        if(matcher.find()) {
            originStart = Long.parseLong(matcher.group(2));
            destinationStart = Long.parseLong(matcher.group(1));
            length = Long.parseLong(matcher.group(3));

            originEnd = originStart + length - 1;
            destinationEnd = destinationStart + length - 1;
            offset = destinationStart-originStart;
        }

    }

    public boolean isInOrigin(Long value) {
        return value >= originStart && value <= originEnd;
    }

    public long map(long originId) {
        return isInOrigin(originId) ? originId + offset : originId;
    }
}
