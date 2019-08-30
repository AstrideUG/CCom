package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class SleepObjective extends ChainObjective {

    private long sleep;

    @Override
    @SneakyThrows
    public void run(CompletableFuture<Object> future, Environment env) {
        Thread.sleep(sleep);
        future.complete(0);
    }
}
