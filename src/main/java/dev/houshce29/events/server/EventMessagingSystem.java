package dev.houshce29.events.server;

import dev.houshce29.events.EventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

/**
 * Main configuration to import into the application that provide full
 * event messaging system functionality.
 *
 * <br />
 *
 * To set up configuration in the front-end, there should be two
 * web socket connections made: one for server-side event listening,
 * and one for client-side event publishing.
 *
 * <br />
 *
 * This library is compatible with SockJS / STOMP. Here is example JS:
 *
 * <pre>
 *
 *     var serverEvents = null;
 *     var clientEvents = null;
 *
 *     function connect() {
 *         serverEvents = Stomp.over(new SockJS('/server'));
 *         clientEvents = Stomp.over(new SockJS('/event'));
 *         serverEvents.connect({}, frame => {
 *             serverEvents.subscribe('/server/event', output => {
 *                 var event = JSON.parse(output.body);
 *                 // ... consume and handle event ...
 *             });
 *         });
 *         clientEvents.connect({}, frame => {});
 *     }
 *
 *     function send() {
 *         clientEvents.send('/client/event', {}, JSON.stringify({
 *             type: 'MyClientSideEvent',
 *             payload: {
 *                 prop1: 'foo',
 *                 prop2: 2,
 *                 prop3: false,
 *                 prop4: {
 *                     prop5: 'bar'
 *                 }
 *             }
 *         }));
 *     }
 *
 * </pre>
 */
@Configuration
@Import(WebSocketConfiguration.class)
public class EventMessagingSystem {

    @Bean
    public WebSocketHandler webSocketHandler(List<EventListener> listeners) {
        return new WebSocketHandler(listeners);
    }

    @Bean
    public WebSocketMessageSender webSocketMessageSender(SimpMessagingTemplate template) {
        return new WebSocketMessageSender(template);
    }

    @Bean
    public ServerEventPublisher serverEventPublisher(WebSocketMessageSender sender) {
        return new ServerEventPublisher(sender);
    }
}
