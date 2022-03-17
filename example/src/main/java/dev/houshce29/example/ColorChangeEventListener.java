package dev.houshce29.example;

import dev.houshce29.events.Event;
import dev.houshce29.events.EventListener;
import dev.houshce29.events.EventPublisher;

import java.util.Map;
import java.util.Set;

public class ColorChangeEventListener implements EventListener {
    private static final Set<String> VALID_COLORS = Set.of(
            "green", "blue", "red", "yellow", "black", "white");
    private final EventPublisher eventPublisher;

    public ColorChangeEventListener(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public String getEventType() {
        return "color-change";
    }

    @Override
    public void onEvent(Event event) {
        String color = String.valueOf(event.getPayload().get("color"));
        Event next = respond(color);
        eventPublisher.publishEvent(next);
    }

    private Event respond(String color) {
        if (VALID_COLORS.contains(color)) {
            return new Event("next-color", Map.of("color", color));
        }
        // Otherwise, return an error event
        return new Event("error", Map.of("message", color + " is not a valid color."));
    }
}
