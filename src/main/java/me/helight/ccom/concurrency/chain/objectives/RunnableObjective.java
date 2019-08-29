package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import me.helight.ccom.concurrency.chain.ChainObjective;

@AllArgsConstructor
public class RunnableObjective extends ChainObjective {

    private Runnable runnable;

    @Override
    public void run() {
        runnable.run();
        finish(null);
    }
}
