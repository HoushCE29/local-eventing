package dev.houshce29.events.server;

import dev.houshce29.events.Event;
import dev.houshce29.events.EventListener;

public class TestEventListener implements EventListener {
    private final String type;
    private boolean eventReceived = false;

    public TestEventListener(String type) {
        this.type = type;
    }

    @Override
    public String getEventType() {
        return type;
    }

    @Override
    public void onEvent(Event event) {
        eventReceived = true;
    }

    public boolean isEventReceived() {
        return eventReceived;
    }
}
