package dev.houshce29.events.server;

import dev.houshce29.events.Event;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;

public class ServerEventPublisherTest {
    private ServerEventPublisher publisher;
    private WebSocketMessageSender sender;

    @Before
    public void beforeEach() {
        sender = Mockito.mock(WebSocketMessageSender.class);
        publisher = new ServerEventPublisher(sender);
    }

    @Test
    public void testPublishEvent() {
        Event event = new Event("test-event", new HashMap<>());
        publisher.publishEvent(event);
        Mockito.verify(sender).sendMessage("/server/event", event);
    }
}
