package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

@AllArgsConstructor
public class SupplierObjective extends ChainObjective {

    private Supplier supplier;

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        future.complete(supplier.get());
    }
}
