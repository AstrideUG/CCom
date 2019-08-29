package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.chain.EnvAdrr;
import me.helight.ccom.concurrency.chain.objectives.ConsumerObjective;
import me.helight.ccom.concurrency.chain.objectives.FunctionObjective;
import me.helight.ccom.concurrency.chain.objectives.RunnableObjective;
import me.helight.ccom.concurrency.chain.objectives.SupplierObjective;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void test() {
        AtomicBoolean runnableCalled = new AtomicBoolean(false);
        long duration = Chain.create()
                .sleep(50)
                .addObjective(new SupplierObjective(() -> 10).exportNamed("mult"))
                .addObjective(new FunctionObjective(a -> (int)((List)a).get(0) * (int)((List)a).get(1), EnvAdrr.from(1), EnvAdrr.from("mult")))
                .sleep(50)
                .addObjective(new RunnableObjective(() -> {
                    runnableCalled.set(true);
                }))
                .addObjective(new ConsumerObjective(x -> assertEquals(10, x), EnvAdrr.from(1)))
                .addObjective(new ConsumerObjective(x -> assertEquals(100, (int)x), EnvAdrr.from(2)))
                .run();

        assertTrue(runnableCalled.get());
        assertTrue(duration >= 100);
    }

    /**
     * Sometimes the assert for c = 50 fails for unknown reasons. However after changes
     * which dont even have to affect the value, it normally works great again
     *
     * If you find a error, which could be responsible for this error, tell me ^^
     */
    @Test
    void testParallel() {
        Chain supplyChain = Chain.create().addObjective(new SupplierObjective(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 50;
        }).exportNamed("val"));
        supplyChain.run();
        assertEquals(supplyChain.environment[0], 50);
        Chain.create()
                .parallel(supplyChain)
                .consume(b -> {
                    System.out.println(b.toString());
                    Object a = ((ArrayList)b).get(0);
                    Object c = ((ArrayList)b).get(1);
                    Object[][] env = (Object[][]) a;
                    assertEquals(50, env[0][0]);
                    assertEquals(50, c);
                }, EnvAdrr.from(0), EnvAdrr.from("val")).run();

    }

}