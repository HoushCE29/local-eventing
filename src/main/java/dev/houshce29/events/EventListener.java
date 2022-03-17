package dev.houshce29.events;

/**
 * Contract for listening to events.
 */
public interface EventListener extends java.util.EventListener {

    /**
     * @return The event type to listen to.
     */
    String getEventType();

    /**
     * Runs some logic upon an event.
     * @param event The event to consume.
     */
    void onEvent(Event event);
}
