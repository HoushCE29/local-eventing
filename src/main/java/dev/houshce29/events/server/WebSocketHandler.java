package dev.houshce29.events.server;

import dev.houshce29.events.Event;
import dev.houshce29.events.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * Web socket handler.
 */
@Controller
class WebSocketHandler {
    private final List<EventListener> listeners;

    public WebSocketHandler(List<EventListener> listeners) {
        this.listeners = listeners;
    }

    /**
     * Invokes the server-side listeners upon receiving an event.
     * This is called by the Spring WebSocket Messaging framework.
     * @param event The event from the client side.
     */
    @MessageMapping("/event")
    public void onClientEvent(Event event) {
        listeners.stream()
                .filter(listener -> listener.getEventType().equals(event.getType()))
                .forEach(listener -> listener.onEvent(event));
    }

    /**
     * Called by the Spring WebSocket Messaging framework, this sends
     * the given event to the client-side for handling.
     * @param event The event to echo back to be sent to client-side.
     * @return The same event that was passed in. This is a goofy looking
     *         impl that takes advantage of the conveniences of Spring.
     */
    @MessageMapping("/server")
    @SendTo("/server/event")
    public Event onServerEvent(Event event) {
        return event;
    }
}
