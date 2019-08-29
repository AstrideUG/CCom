package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.function.Consumer;
import java.util.function.Function;

@AllArgsConstructor
public class FunctionObjective extends ChainObjective {

    private Function function;

    public FunctionObjective(Function function, Integer... envs) {
        this.function = function;
        this.envAddrs = envs;
    }

    @Override
    @SneakyThrows
    public void run() {
        finish(function.apply(compressEnv()));
    }
}
