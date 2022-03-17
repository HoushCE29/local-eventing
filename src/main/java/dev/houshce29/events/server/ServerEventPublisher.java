package dev.houshce29.events.server;

import dev.houshce29.events.Event;
import dev.houshce29.events.EventPublisher;

/**
 * Server-side event publisher.
 */
public class ServerEventPublisher implements EventPublisher {
    private final WebSocketMessageSender sender;

    public ServerEventPublisher(WebSocketMessageSender sender) {
        this.sender = sender;
    }

    @Override
    public void publishEvent(Event event) {
        sender.sendMessage("/server/event", event);
    }
}
