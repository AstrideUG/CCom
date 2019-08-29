package me.helight.ccom.concurrency;

import lombok.Getter;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.objectives.SleepObjective;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.WeakHashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Chain {

    private ArrayList<ChainObjective> objectives = new ArrayList<>();

    public Object[] environment;

    public Chain addObjective(ChainObjective objective) {
        objectives.add(objective);
        return this;
    }

    public Chain sleep(long millis) {
        objectives.add(new SleepObjective(millis));
        return this;
    }

    public long run() {
        environment = new Object[objectives.size()];
        long timestamp = System.currentTimeMillis();

        for (int i = 0; i < objectives.size(); i++) {
            ChainObjective chainObjective = objectives.get(i);
            chainObjective.exec(this);
            try {
                environment[i] = chainObjective.getFuture().get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return System.currentTimeMillis() - timestamp;
    }

    public Future<Long> runAsync() {
        CompletableFuture<Long> future = new CompletableFuture<>();

        new Thread(() -> {
            future.complete(run());
        }).start();

        return future;
    }

    public static Chain create() {
        return new Chain();
    }

}
