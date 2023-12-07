package com.adventofcode.flashk.day05;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Getter
@Setter
public class Seed {

    private final long id;
    private long soil = -1;
    private long fertilizer = -1;
    private long water = -1;
    private long light = -1;
    private long temperature = -1;
    private long humidity = -1;
    private long location = -1;

    public Seed(Long id) {
        this.id = id;
    }

    public Seed(String id) {
        this.id = Long.parseLong(id);
    }

    @Override
    public String toString() {
        return String.format("Seed %d, soil %d, fertilizer %d, water %d, light %d, temperature %d, humidity %d, location %d",
        id, soil, fertilizer, water, light, temperature, humidity, location);
    }

}
