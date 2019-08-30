package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.EnvAdrr;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@AllArgsConstructor
public class FunctionObjective extends ChainObjective {

    private Function function;

    public FunctionObjective(Function function, EnvAdrr... envs) {
        this.function = function;
        this.envAddrs = envs;
    }

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        future.complete(function.apply(env.resolve(envAddrs)));
    }
}
