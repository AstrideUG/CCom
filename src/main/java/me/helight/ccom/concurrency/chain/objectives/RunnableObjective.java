package me.helight.ccom.concurrency.chain.objectives;

import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;

public class RunnableObjective extends ChainObjective {

    private Runnable runnable;

    public RunnableObjective(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        runnable.run();
        future.complete(0);
    }
}
