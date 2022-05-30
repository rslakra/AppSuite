package com.rslakra.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/11/22 6:10 PM
 */
public class StopWatch {

    private static Logger LOGGER = LoggerFactory.getLogger(StopWatch.class);
    private Instant startTime;
    private Instant endTime;
    private Duration duration;

    public StopWatch() {
    }

    public void startTimer() {
        startTime = Instant.now();
    }

    public void endTimer() {
        endTime = Instant.now();
        duration = Duration.between(startTime, endTime);
    }

    /**
     * @return
     */
    private String timeTaken() {
        return String.format("%dD, %02d:%02d:%02d.%04d", duration.toDays(), duration.toHours(), duration.toMinutes(),
                             duration.getSeconds(), duration.toMillis());
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(StopWatch.class)
            .add("startTime", startTime)
            .add("endTime", endTime)
            .add("duration", timeTaken())
            .toString();
    }

}
