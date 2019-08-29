package me.helight.ccom.concurrency;

import lombok.Getter;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.objectives.*;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chain {

    private ArrayList<ChainObjective> objectives = new ArrayList<>();

    public Object[] environment;

    public Chain parent;

    public Chain addObjective(ChainObjective objective) {
        objectives.add(objective);
        return this;
    }

    /**
     * Shortcut for adding a {@link SleepObjective} to the ChainCall
     */
    public Chain sleep(long millis) {
        objectives.add(new SleepObjective(millis));
        return this;
    }

    public Chain supply(Supplier supplier) {
        objectives.add(new SupplierObjective(supplier));
        return this;
    }

    public Chain runnable(Runnable runnable) {
        objectives.add(new RunnableObjective(runnable));
        return this;
    }

    public Chain futur(Future future) {
        objectives.add(new FutureObjective(future));
        return this;
    }

    public Chain consume(Consumer consumer, Integer... environmentAdresses) {
        objectives.add(new ConsumerObjective(consumer, environmentAdresses));
        return this;
    }

    public Chain function(Function function, Integer... environmentAdresses) {
        objectives.add(new FunctionObjective(function,environmentAdresses));
        return this;
    }

    public Chain chain(Chain chain) {
        objectives.add(new ChainedObjective(chain));
        return this;
    }


    public Chain parallel(Chain... chains) {
        objectives.add(new ParallelObjective(chains));
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
