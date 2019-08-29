package me.helight.ccom.concurrency.chain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import me.helight.ccom.concurrency.Chain;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
public abstract class ChainObjective {

    public Chain chain;

    protected EnvAdrr[] envAddrs;

    private String exportEnv = null;

    @Getter
    private CompletableFuture<Object> future = new CompletableFuture<>();

    protected Object compressEnv() {
        if (envAddrs.length == 1 ) {
            return envAddrs[0].resolve(chain);
        } else {
            ArrayList<Object> arrayList = new ArrayList<>();
            for (EnvAdrr addr : envAddrs) {
                arrayList.add(addr.resolve(chain));
            }
            return arrayList;
        }
    }

    protected final void finish(Object object) {
        if (exportEnv != null) {
            EnvAdrr.getHighestChain(chain).getNamedEnvironment().put(exportEnv, object);
        }

        future.complete(object);
    }

    protected abstract void run();

    public final ChainObjective exportNamed(String key) {
        exportEnv = key;
        return this;
    }

    public final void exec(Chain chain) {
        this.chain = chain;
        run();
    }

}
