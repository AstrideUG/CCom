package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import me.helight.ccom.concurrency.Chain;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.HashMap;

@AllArgsConstructor
public class ChainedObjective extends ChainObjective {

    private Chain c;

    @Override
    public void run() {
        c.run(new HashMap<>());
        finish(c.environment);
    }
}
