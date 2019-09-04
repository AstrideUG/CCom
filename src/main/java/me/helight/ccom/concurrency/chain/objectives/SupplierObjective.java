package me.helight.ccom.concurrency.chain.objectives;

import me.helight.ccom.concurrency.Environment;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class SupplierObjective extends ChainObjective {

    private Supplier supplier;

    public SupplierObjective(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public void run(CompletableFuture<Object> future, Environment env) {
        future.complete(supplier.get());
    }
}
