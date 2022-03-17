package dev.houshce29.events.server;

import dev.houshce29.events.Event;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

public class WebSocketHandlerTest {
    private WebSocketHandler handler;
    private TestEventListener fooListener;
    private TestEventListener barListener;

    @Before
    public void beforeEach() {
        fooListener = new TestEventListener("foo");
        barListener = new TestEventListener("bar");
        handler = new WebSocketHandler(Arrays.asList(fooListener, barListener));
    }

    @Test
    public void testOnClientEvent() {
        Event event = new Event(fooListener.getEventType(), new HashMap<>());
        handler.onClientEvent(event);
        Assert.assertTrue(fooListener.isEventReceived());
        Assert.assertFalse(barListener.isEventReceived());
    }

    @Test
    public void testOnServerEvent() {
        Event event = new Event("my-server-event", new HashMap<>());
        Event sent = handler.onServerEvent(event);
        Assert.assertEquals(event, sent);
    }
}
