package me.helight.ccom.concurrency.chain.objectives;

import lombok.SneakyThrows;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class FutureObjective extends ChainObjective {

    private Future<Object> future;

    public FutureObjective(Future<Object> future) {
        this.future = future;
    }

    @Override
    @SneakyThrows
    public void run(CompletableFuture<Object> f, Environment env) {
        f.complete(future.get(60, TimeUnit.SECONDS));
    }
}
