package me.helight.ccom.concurrency.chain.objectives;

import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class ParallelObjective extends ChainObjective {

    private Chain[] chains;

    public ParallelObjective(Chain... chains) {
        this.chains = chains;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        ParallelObjective chainObjective = (ParallelObjective) super.clone();
        chainObjective.chains=Arrays.copyOf(chains, chains.length);
        return chainObjective;
    }

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        CountDownLatch latch = new CountDownLatch(chains.length);
        for (int i = 0; i < chains.length; i++) {
            int finalI = i;
            new Thread(() -> {
                Chain c = (Chain) chains[finalI];
                c.run(env);
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            future.complete(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
