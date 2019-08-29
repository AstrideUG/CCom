package me.helight.ccom.concurrency.chain;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.helight.ccom.concurrency.Chain;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RequiredArgsConstructor
public abstract class ChainObjective {

    protected Chain chain;

    protected Integer[] envAddrs;

    @Getter
    private CompletableFuture<Object> future = new CompletableFuture<>();

    protected Object compressEnv() {
        if (envAddrs.length == 1 ) {
            return chain.environment[envAddrs[0]];
        } else {
            ArrayList<Object> arrayList = new ArrayList<>();
            for (int i : envAddrs) {
                arrayList.add(chain.environment[i]);
            }
            return arrayList;
        }
    }

    protected final void finish(Object object) {
        future.complete(object);
    }

    protected abstract void run();

    public final void exec(Chain chain) {
        this.chain = chain;
        run();
    }

}
