package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class RunnableObjective extends ChainObjective {

    private Runnable runnable;

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        runnable.run();
        future.complete(0);
    }
}
