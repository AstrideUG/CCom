package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.chain.ChainObjective;
import me.helight.ccom.concurrency.chain.objectives.*;
import org.junit.jupiter.api.Test;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void test() {
        AtomicBoolean runnableCalled = new AtomicBoolean(false);
        long duration = Chain.create()
                .sleep(100)
                .addObjective(new SupplierObjective(() -> 10))
                .addObjective(new FunctionObjective(i -> (int)i * 10, 1))
                .sleep(100)
                .addObjective(new RunnableObjective(() -> {
                    runnableCalled.set(true);
                }))
                .addObjective(new ConsumerObjective(x -> {
                    assertEquals(10, x);
                }, 1))
                .addObjective(new ConsumerObjective(x -> {
                    assertEquals(100, x);
                }, 2))
                .run();

        assertTrue(runnableCalled.get());
        assertTrue(duration >= 200);
    }

    @Test
    void testParallel() {
        Chain supplyChain = Chain.create().supply(() -> 50);
        supplyChain.run();
        assertEquals(supplyChain.environment[0], 50);
        Chain.create()
                .parallel(supplyChain)
                .consume(a -> {
                    Object[][] env = (Object[][]) a;
                    assertEquals(env[0][0], 50);
                }, 0).run();

    }

}