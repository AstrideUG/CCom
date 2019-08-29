package me.helight.ccom.concurrency.chain.objectives;

import lombok.SneakyThrows;
import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CountDownLatch;

public class ParallelObjective extends ChainObjective {

    private Chain[] chains;

    public ParallelObjective(Chain... chains) {
        this.chains = chains;
    }

    @Override
    @SneakyThrows
    public void run() {
        Object[][] returns = new Object[chains.length][];
        CountDownLatch latch = new CountDownLatch(chains.length);
        for (int i = 0; i < chains.length; i++) {
            int finalI = i;
            new Thread(() -> {
                Chain c = chains[finalI];
                c.parent = chain;
                c.run();
                returns[finalI] = c.environment;
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
            finish(returns);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
