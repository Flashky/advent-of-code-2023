package com.adventofcode.flashk.day06;

public class Race {

    private final long time;
    private final long recordDistance;

    public Race(long time, long recordDistance) {
        this.time = time;
        this.recordDistance = recordDistance;
    }

    public long beatTheRecord() {

        long speed = 0;
        long remainingTime = time;
        long waysToBeatRecord = 0;

        for(long i = 1; i < time-1; i++) {
            speed++;
            remainingTime--;
            long distance = remainingTime * speed;
            if(distance > recordDistance) {
                waysToBeatRecord++;
            }
        }

        return waysToBeatRecord;
    }
}
