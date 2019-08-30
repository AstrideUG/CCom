package me.helight.ccom.concurrency.chain;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.Environment;

import java.io.Serializable;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public abstract class ChainObjective implements Cloneable, Serializable {

    public Chain chain;

    protected EnvAdrr[] envAddrs;

    private EnvAdrr exportEnv = null;

    public abstract void run(CompletableFuture<Object> future, Environment env);

    public final ChainObjective exportNamed(String key) {
        exportEnv = EnvAdrr.from(key);
        return this;
    }

    public final ChainObjective exportNamed(EnvAdrr addr) {
        exportEnv = addr;
        return this;
    }

    @SneakyThrows
    public final void exec(Environment environment) {
        ChainObjective chainObjective = (ChainObjective) clone();
        chainObjective.chain = chain;
        CompletableFuture<Object> future = new CompletableFuture<>();
        chainObjective.run(future, environment);
        Object object = future.get();

        if (exportEnv != null) {
            exportEnv.set(environment, object);
        }
    }
}
