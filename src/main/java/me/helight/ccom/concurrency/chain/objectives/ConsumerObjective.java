package me.helight.ccom.concurrency.chain.objectives;

import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.EnvAdrr;

import java.util.function.Consumer;

public class ConsumerObjective extends ChainObjective {

    private Consumer consumer;

    public ConsumerObjective(Consumer consumer, EnvAdrr... envs) {
        this.consumer = consumer;
        this.envAddrs = envs;
    }

    @Override
    @SneakyThrows
    public void run() {
        consumer.accept(compressEnv());
        finish(null);
    }
}
