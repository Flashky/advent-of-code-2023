package com.adventofcode.flashk.day06;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WaitForIt {

    private static final Pattern NUMBER_PATTERN = Pattern.compile(" +(\\d*)");
    private final List<Race> races = new ArrayList<>();

    public WaitForIt(List<String> inputs, boolean isPartOne) {

        if(isPartOne) {
            initPartOne(inputs);
        } else {
            initPartTwo(inputs);
        }

    }

    private void initPartOne(List<String> inputs) {
        String times = inputs.get(0).split(":")[1];
        String records = inputs.get(1).split(":")[1];

        Matcher timesMatcher = NUMBER_PATTERN.matcher(times);
        List<Integer> timesList = new ArrayList<>();
        while(timesMatcher.find()) {
            timesList.add(Integer.parseInt(timesMatcher.group(1)));
        }

        Matcher recordsMatcher = NUMBER_PATTERN.matcher(records);
        List<Integer> recordsList = new ArrayList<>();
        while(recordsMatcher.find()) {
            recordsList.add(Integer.parseInt((recordsMatcher.group(1))));
        }

        for(int i = 0; i < timesList.size(); i++) {
            races.add(new Race(timesList.get(i), recordsList.get(i)));
        }
    }

    private void initPartTwo(List<String> inputs) {
        String times = inputs.get(0).split(":")[1];
        String records = inputs.get(1).split(":")[1];

        times = StringUtils.getDigits(times);
        records = StringUtils.getDigits(records);

        races.add(new Race(Long.parseLong(times), Long.parseLong(records)));
    }

    public long solve() {
        return races.stream().map(Race::beatTheRecord).reduce(1L, Math::multiplyExact);
    }
}
