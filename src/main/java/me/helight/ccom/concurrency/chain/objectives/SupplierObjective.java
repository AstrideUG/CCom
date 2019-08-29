package me.helight.ccom.concurrency.chain.objectives;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import me.helight.ccom.concurrency.chain.ChainObjective;

import java.util.concurrent.Future;
import java.util.function.Supplier;

@AllArgsConstructor
public class SupplierObjective extends ChainObjective {

    private Supplier supplier;

    @Override
    @SneakyThrows
    public void run() {
        finish(supplier.get());
    }
}
