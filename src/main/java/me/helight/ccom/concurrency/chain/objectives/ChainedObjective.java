package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.ChainResult;

import java.util.concurrent.CompletableFuture;

@AllArgsConstructor
public class ChainedObjective extends ChainObjective {

    private Chain c;

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        ChainResult result = c.run(env);
        future.complete(result);
    }
}
