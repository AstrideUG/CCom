package me.helight.ccom.concurrency.chain;

import me.helight.ccom.concurrency.Environment;

public class ChainResult {

    private long duration;
    private Environment environment;

    public ChainResult(long duration, Environment environment) {
        this.duration = duration;
        this.environment = environment;
    }

    public long getDuration() {
        return this.duration;
    }

    public Environment getEnvironment() {
        return this.environment;
    }
}
