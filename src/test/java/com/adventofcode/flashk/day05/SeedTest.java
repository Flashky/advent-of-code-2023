package com.adventofcode.flashk.day05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeedTest {

    @Test
    void toStringTest() {
        Seed seed = new Seed(1L);
        seed.setSoil(2);
        seed.setFertilizer(3);
        seed.setWater(4);
        seed.setLight(5);
        seed.setTemperature(6);
        seed.setHumidity(7);
        seed.setLocation(8);

        assertEquals("Seed 1, soil 2, fertilizer 3, water 4, light 5, temperature 6, humidity 7, location 8", seed.toString());

    }
}
