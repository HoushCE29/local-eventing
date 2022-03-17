package dev.houshce29.events.server;

import org.springframework.messaging.simp.SimpMessagingTemplate;

/**
 * Bean that sends messages to web sockets.
 */
class WebSocketMessageSender {
    private final SimpMessagingTemplate template;

    public WebSocketMessageSender(SimpMessagingTemplate template) {
        this.template = template;
    }

    /**
     * Sends a message to the given path.
     * @param path The path of the web socket.
     * @param message The message.
     */
    public void sendMessage(String path, Object message) {
        template.convertAndSend(path, message);
    }
}
