package dev.houshce29.events.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.Collections;

public class EventMessagingSystemTest {
    private EventMessagingSystem system;

    @Before
    public void beforeEach() {
        system = new EventMessagingSystem();
    }

    @Test
    public void testConfig() {
        Assert.assertNotNull(EventMessagingSystem.class.getAnnotation(Configuration.class));
        Import imports = EventMessagingSystem.class.getAnnotation(Import.class);
        Assert.assertEquals(1, imports.value().length);
        Assert.assertEquals(WebSocketConfiguration.class, imports.value()[0]);
    }

    @Test
    public void testWebSocketHandler() {
        Assert.assertNotNull(system.webSocketHandler(Collections.emptyList()));
    }

    @Test
    public void testWebSocketMessageSender() {
        Assert.assertNotNull(system.webSocketMessageSender(Mockito.mock(SimpMessagingTemplate.class)));
    }

    @Test
    public void testServerEventPublisher() {
        Assert.assertNotNull(system.serverEventPublisher(Mockito.mock(WebSocketMessageSender.class)));
    }
}
