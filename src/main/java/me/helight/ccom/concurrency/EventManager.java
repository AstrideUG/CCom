package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.event.Listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventManager<K> {

    private List<Listener<K>> listeners = Collections.synchronizedList(new ArrayList<>());

    public void broadcast(K k) {
        for (Listener<K> listener : listeners) {
            if (listener.validate(k)) {
                listener.handle(k);
            }
        }
    }

    public boolean isRegistered(Listener<K> listener) {
        return listeners.contains(listener);
    }

    public void registerListener(Listener<K> listener) {
        listeners.add(listener);
    }

    public void unregisterListener(Listener<K> listener) {
        listeners.remove(listener);
    }

}
