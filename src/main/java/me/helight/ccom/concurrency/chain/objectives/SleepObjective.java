package me.helight.ccom.concurrency.chain.objectives;

import lombok.SneakyThrows;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;

public class SleepObjective extends ChainObjective {

    private long sleep;

    public SleepObjective(long sleep) {
        this.sleep = sleep;
    }

    @Override
    @SneakyThrows
    public void run(CompletableFuture<Object> future, Environment env) {
        Thread.sleep(sleep);
        future.complete(0);
    }
}
