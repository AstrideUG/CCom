package me.helight.ccom.concurrency;

import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainResult;
import me.helight.ccom.concurrency.chain.EnvAdrr;
import me.helight.ccom.concurrency.chain.objectives.ConsumerObjective;
import me.helight.ccom.concurrency.chain.objectives.FunctionObjective;
import me.helight.ccom.concurrency.chain.objectives.RunnableObjective;
import me.helight.ccom.concurrency.chain.objectives.SupplierObjective;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

class ChainTest {

    @Test
    void test() {
        AtomicBoolean runnableCalled = new AtomicBoolean(false);

        int xRandom = ThreadLocalRandom.current().nextInt();
        int yRandom = ThreadLocalRandom.current().nextInt();

        Environment map = new Environment();
        map.put("mult", yRandom);

        ChainResult result = Chain.create()
                .sleep(50)
                .addObjective(new SupplierObjective(() -> xRandom)).export(EnvAdrr.from("org"))
                .addObjective(new FunctionObjective(a -> (int)((List)a).get(0) * (int)((List)a).get(1), EnvAdrr.from("org"), EnvAdrr.from("mult")).exportNamed("result"))
                .sleep(50)
                .addObjective(new RunnableObjective(() -> {
                    runnableCalled.set(true);
                }))
                .addObjective(new ConsumerObjective(x -> assertEquals(xRandom, x), EnvAdrr.from("org")))
                .addObjective(new ConsumerObjective(x -> assertEquals(xRandom * yRandom, (int)x), EnvAdrr.from("result")))
                .consume(x -> assertEquals(xRandom, x), Integer.class).adresses(EnvAdrr.from("org"))
                .run(map);

        assertTrue(runnableCalled.get());
        assertTrue(result.getDuration() >= 100);
    }

    @Test
    @SneakyThrows
    void testReset() {
        Chain chain = Chain.create()
                .chain(Chain.create().addObjective(new FunctionObjective(p -> {
                    assertNotNull(p);
                    return p;
                }, EnvAdrr.from("origin")).exportNamed("uno")))
                .addObjective(new FunctionObjective(s -> {
                    List<Integer> ints = (List<Integer>)s;
                    return ints.get(0) * ints.get(1);
                }, EnvAdrr.from("uno"), EnvAdrr.from("mult")).exportNamed("result"));

        assertNotNull(chain);

        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    Environment env = new Environment();
                    int org = ThreadLocalRandom.current().nextInt();
                    int mult = ThreadLocalRandom.current().nextInt();
                    System.out.println("Numbers are: " + org + "," + mult);
                    env.put("origin", org);
                    env.put("mult", mult);
                    env.put("#" + ThreadLocalRandom.current().nextInt(), ThreadLocalRandom.current().nextInt());

                    ChainResult chainResult = chain.runAsync(env).get();
                    assertEquals(org * mult, chainResult.getEnvironment().get("result"));
                    assertEquals(5, chainResult.getEnvironment().size());
                    System.out.println("Assert successful");
                    countDownLatch.countDown();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        countDownLatch.await();

    }

    @Test
    void testParallel() {
        int i = ThreadLocalRandom.current().nextInt();

        Chain supplyChain = Chain.create().addObjective(new SupplierObjective(() -> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return i;
        }).exportNamed("val"));
        ChainResult firstResult = supplyChain.run(new Environment());
        assertEquals(firstResult.getEnvironment().get("val"), i);
        Chain.create()
                .parallel(supplyChain)
                .consume(b -> assertEquals(i, b), EnvAdrr.from("val")).run(new Environment());

    }

}