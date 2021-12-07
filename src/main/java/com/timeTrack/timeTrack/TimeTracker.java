package com.timeTrack.timeTrack;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;

public class TimeTracker {

    private final LocalDateTime startTime;
    private LocalDateTime endTime;
    //private LocalDateTime pauseTime;
    private Duration totalTime;


    public TimeTracker(){
        startTime = LocalDateTime.now();
    }

    public void setEndTime(){
        endTime = LocalDateTime.now();
    }

    public Duration getTotalTime(){
        return totalTime = Duration.between(startTime, endTime);
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }
}
