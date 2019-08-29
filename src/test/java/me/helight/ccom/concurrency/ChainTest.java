package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.chain.objectives.ConsumerObjective;
import me.helight.ccom.concurrency.chain.objectives.RunnableObjective;
import me.helight.ccom.concurrency.chain.objectives.SupplierObjective;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void test() {
        AtomicBoolean runnableCalled = new AtomicBoolean(false);
        long duration = Chain.create()
                .sleep(100)
                .addObjective(new SupplierObjective(() -> 10))
                .sleep(100)
                .addObjective(new RunnableObjective(() -> {
                    runnableCalled.set(true);
                }))
                .addObjective(new ConsumerObjective(first -> {
                    assertEquals(10, first);
                }, 1))
                .run();

        assertTrue(runnableCalled.get());
        assertTrue(duration >= 200);
    }

}