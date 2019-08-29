package me.helight.ccom.concurrency;

import me.helight.ccom.concurrency.event.Listener;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventManagerTest {

    private EventManager<String> eventManager = new EventManager<>();

    private Listener<String> listener = event -> assertNotNull(event);

    @Test
    void register() {
        eventManager.registerListener(listener);
        assertTrue(eventManager.isRegistered(listener));
    }

    @Test
    void broadcast() {
        eventManager.broadcast("123");
    }

    @Test
    void unregister() {
        eventManager.unregisterListener(listener);
        assertFalse(eventManager.isRegistered(listener));
    }
}