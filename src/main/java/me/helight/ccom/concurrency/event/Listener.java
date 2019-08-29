package me.helight.ccom.concurrency.event;

public interface Listener<K> extends Validator<K> {

    void handle(K event);
}
