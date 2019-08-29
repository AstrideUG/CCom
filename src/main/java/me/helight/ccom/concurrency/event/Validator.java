package me.helight.ccom.concurrency.event;

public interface Validator<K> {

    default boolean validate(K k) {
        return true;
    };
}
