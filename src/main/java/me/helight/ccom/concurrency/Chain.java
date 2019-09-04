package me.helight.ccom.concurrency;

import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.ChainResult;
import me.helight.ccom.concurrency.chain.EnvAdrr;
import me.helight.ccom.concurrency.chain.objectives.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Chain {

    private List<ChainObjective> objectives = Collections.synchronizedList(new ArrayList<>());

    private Map<String,Object> namedEnvironment = new ConcurrentHashMap<>();

    public Chain addObjective(ChainObjective objective) {
        objectives.add(objective);
        return this;
    }

    public Chain sleep(long millis) {
        objectives.add(new SleepObjective(millis));
        return this;
    }

    public Chain env(String key, Object value) {
        namedEnvironment.put(key, value);
        return this;
    }

    public Chain export(EnvAdrr envAdrr) {
        int i = objectives.size() - 1;
        objectives.get(i).exportNamed(envAdrr);
        return this;
    }

    public Chain adresses(EnvAdrr... addr) {
        int i = objectives.size() - 1;
        objectives.get(i).setEnvAddrs(addr);
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

    public Chain future(Future future) {
        objectives.add(new FutureObjective(future));
        return this;
    }

    public Chain consume(Consumer consumer, EnvAdrr... environmentAdresses) {
        objectives.add(new ConsumerObjective(consumer, environmentAdresses));
        return this;
    }

    public <K> Chain consume(Consumer<K> consumer, Class<K> clazz) {
        objectives.add(new ConsumerObjective(consumer));
        return this;
    }

    public Chain function(Function function, EnvAdrr... environmentAdresses) {
        objectives.add(new FunctionObjective(function,environmentAdresses));
        return this;
    }

    public <K,V> Chain function(Function<K,V> function, Class<K> clazz) {
        objectives.add(new FunctionObjective(function));
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

    @SneakyThrows
    public ChainResult run(Environment env) {
        long timestamp = System.currentTimeMillis();

        for (ChainObjective chainObjective  : objectives) {
            CountDownLatch blocker = new CountDownLatch(1);
            new Thread(() -> {
                chainObjective.exec(env);
                blocker.countDown();
            }).start();
            blocker.await(10, TimeUnit.SECONDS);
        }

        long duration = System.currentTimeMillis() - timestamp;
        return new ChainResult(duration,env);
    }

    public Future<ChainResult> runAsync(Environment env) {
        CompletableFuture<ChainResult> future = new CompletableFuture<>();

        new Thread(() -> {
            future.complete(run(env));
        }).start();

        return future;
    }

    public static Chain create() {
        return new Chain();
    }

    public Map<String, Object> getNamedEnvironment() {
        return this.namedEnvironment;
    }

    private void setObjectives(List<ChainObjective> objectives) {
        this.objectives = objectives;
    }

    public void setNamedEnvironment(Map<String, Object> namedEnvironment) {
        this.namedEnvironment = namedEnvironment;
    }
}
