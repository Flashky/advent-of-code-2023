package com.adventofcode.flashk.day05;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YouGiveASeedAFertilizer {

    private static final Pattern NUMBER_PATTERN = Pattern.compile("(\\d*)");
    private static final Pattern SEED_RANGE_PATTERN = Pattern.compile(" +(\\d*) (\\d*)");

    private final String initialSeedsInfo;
    private final List<Seed> initialSeeds = new ArrayList<>();
    private final List<RowRange> seedToSoil = new ArrayList<>();
    private final List<RowRange> soilToFertilizer = new ArrayList<>();
    private final List<RowRange> fertilizerToWater = new ArrayList<>();
    private final List<RowRange> waterToLight = new ArrayList<>();
    private final List<RowRange> lightToTemperature = new ArrayList<>();
    private final List<RowRange> temperatureToHumidity = new ArrayList<>();
    private final List<RowRange> humidityToLocation = new ArrayList<>();

    public YouGiveASeedAFertilizer(List<String> inputs) {

        initialSeedsInfo = inputs.get(0);

        // Lectura: seed-to-soil-map
        inputs.remove(0);
        inputs.remove(1);

        int mapId = 0;

        for (String input : inputs) {
            if (input.contains("map")) {
                mapId++;
            } else if (StringUtils.isNotBlank(input)) {
                addRowRange(mapId, input);
            }
        }

    }

    private void addRowRange(int mapId, String input) {

        RowRange rowRange = new RowRange(input);
        switch (mapId) {
            case 0 -> seedToSoil.add(rowRange);
            case 1 -> soilToFertilizer.add(rowRange);
            case 2 -> fertilizerToWater.add(rowRange);
            case 3 -> waterToLight.add(rowRange);
            case 4 -> lightToTemperature.add(rowRange);
            case 5 -> temperatureToHumidity.add(rowRange);
            case 6 -> humidityToLocation.add(rowRange);
            default -> throw new IllegalArgumentException("Unexistent map id");
        }
    }

    public long solveA() {

        Matcher seedsMatcher = NUMBER_PATTERN.matcher(initialSeedsInfo);
        while (seedsMatcher.find()) {
            if (StringUtils.isNotBlank(seedsMatcher.group(1))) {
                initialSeeds.add(new Seed(seedsMatcher.group(1)));
            }
        }

        for (Seed seed : initialSeeds) {
            mapSoil(seed);
            mapFertilizer(seed);
            mapWater(seed);
            mapLight(seed);
            mapTemperature(seed);
            mapHumidity(seed);
            mapLocation(seed);
        }

        return initialSeeds.stream().map(Seed::getLocation).min(Long::compareTo).get();
    }

    public long solveB() {
        long result = Long.MAX_VALUE;

        // Process by ranges
        Matcher seedsMatcher = SEED_RANGE_PATTERN.matcher(initialSeedsInfo);
        while (seedsMatcher.find()) {
            long startIndex = Long.parseLong(seedsMatcher.group(1));
            long range = Long.parseLong(seedsMatcher.group(2));
            long endIndex = startIndex+range;

            for(long i = startIndex; i < endIndex; i++) {
                Seed seed = new Seed(i);
                mapSoil(seed);
                mapFertilizer(seed);
                mapWater(seed);
                mapLight(seed);
                mapTemperature(seed);
                mapHumidity(seed);
                mapLocation(seed);
                result = Math.min(result, seed.getLocation());
            }

        }

        return result;
    }


    private void mapSoil(Seed seed) {
        long originId = seed.getId();
        Optional<RowRange> range = seedToSoil.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long soilId = range.get().map(originId);
            seed.setSoil(soilId);
        } else {
            seed.setSoil(originId);
        }
    }

    private void mapFertilizer(Seed seed) {

        long originId = seed.getSoil();
        Optional<RowRange> range = soilToFertilizer.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long fertilizerId = range.get().map(originId);
            seed.setFertilizer(fertilizerId);
        } else {
            seed.setFertilizer(originId);
        }
    }

    private void mapWater(Seed seed) {
        long originId = seed.getFertilizer();
        Optional<RowRange> range = fertilizerToWater.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long waterId = range.get().map(originId);
            seed.setWater(waterId);
        } else {
            seed.setWater(originId);
        }
    }

    private void mapLight(Seed seed) {
        long originId = seed.getWater();
        Optional<RowRange> range = waterToLight.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long lightId = range.get().map(originId);
            seed.setLight(lightId);
        } else {
            seed.setLight(originId);
        }
    }

    private void mapTemperature(Seed seed) {
        long originId = seed.getLight();
        Optional<RowRange> range = lightToTemperature.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long temperatureId = range.get().map(originId);
            seed.setTemperature(temperatureId);
        } else {
            seed.setTemperature(originId);
        }
    }

    private void mapHumidity(Seed seed) {
        long originId = seed.getTemperature();
        Optional<RowRange> range = temperatureToHumidity.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long humidityId = range.get().map(originId);
            seed.setHumidity(humidityId);
        } else {
            seed.setHumidity(originId);
        }
    }

    private void mapLocation(Seed seed) {
        long originId = seed.getHumidity();
        Optional<RowRange> range = humidityToLocation.stream()
                .filter(rg -> rg.isInOrigin(originId))
                .findFirst();

        if (range.isPresent()) {
            long locationId = range.get().map(originId);
            seed.setLocation(locationId);
        } else {
            seed.setLocation(originId);
        }
    }
}

