package me.helight.ccom.concurrency.chain.objectives;

import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.EnvAdrr;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ConsumerObjective extends ChainObjective {

    private Consumer consumer;

    public ConsumerObjective(Consumer consumer, EnvAdrr... envs) {
        this.consumer = consumer;
        this.envAddrs = envs;
    }

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        consumer.accept(env.resolve(envAddrs));
        future.complete(0);
    }
}
