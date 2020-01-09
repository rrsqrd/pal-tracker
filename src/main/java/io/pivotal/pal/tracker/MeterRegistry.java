package io.pivotal.pal.tracker;

public class MeterRegistry {
    private DistributionSummary timeEntrySummary;
    private Counter actionCounter;

    public MeterRegistry(DistributionSummary timeEntrySummary, Counter actionCounter) {
        this.timeEntrySummary = timeEntrySummary;
        this.actionCounter = actionCounter;
    }

    public DistributionSummary summary(String s) {
        if (s == "timeEntry.summary") {
            return timeEntrySummary;
        }
        return null;
    }

    public Counter counter(String s) {
        if (s == "timeEntry.actionCounter") {
            return actionCounter;
        }
        return null;
    }
}
