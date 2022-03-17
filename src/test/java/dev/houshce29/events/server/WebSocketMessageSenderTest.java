package dev.houshce29.events.server;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class WebSocketMessageSenderTest {
    private WebSocketMessageSender sender;
    private SimpMessagingTemplate template;

    @Before
    public void beforeEach() {
        template = Mockito.mock(SimpMessagingTemplate.class);
        sender = new WebSocketMessageSender(template);
    }

    @Test
    public void testSendMessage() {
        sender.sendMessage("/foo/bar", "Hello, world!");
        Mockito.verify(template).convertAndSend("/foo/bar", "Hello, world!");
    }
}
