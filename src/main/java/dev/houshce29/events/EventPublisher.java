package dev.houshce29.events;

/**
 * Contract to publish events.
 */
public interface EventPublisher {

    /**
     * Publishes the given event.
     * @param event The event to publish.
     */
    void publishEvent(Event event);
}
