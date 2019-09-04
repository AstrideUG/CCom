package me.helight.ccom.concurrency.chain.objectives;

import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.ChainResult;

import java.util.concurrent.CompletableFuture;

public class ChainedObjective extends ChainObjective {

    private Chain c;

    public ChainedObjective(Chain c) {
        this.c = c;
    }

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        ChainResult result = c.run(env);
        future.complete(result);
    }
}
