package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.chain.objectives.ConsumerObjective;
import me.helight.ccom.concurrency.chain.objectives.SupplierObjective;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void test() {
        long duration = Chain.create()
                .sleep(100)
                .addObjective(new SupplierObjective(() -> 10))
                .sleep(100)
                .addObjective(new ConsumerObjective(first -> {
                    assertEquals(10, first);
                }, 1))
                .run();

        assertTrue(duration >= 200);
    }

}