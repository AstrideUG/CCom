package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

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
            Chain c = chains[i];
            c.parent = chain;
            new Thread(() -> {
                c.run();
                returns[finalI] = c.environment;
                latch.countDown();
            }).start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        finish(returns);
    }
}
