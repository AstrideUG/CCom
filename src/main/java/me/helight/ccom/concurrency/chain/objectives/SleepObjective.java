package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
public class SleepObjective extends ChainObjective {

    private long sleep;

    @Override
    @SneakyThrows
    public void run() {
        Thread.sleep(sleep);
        finish(sleep);
    }
}
