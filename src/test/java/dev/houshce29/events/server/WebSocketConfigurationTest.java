package dev.houshce29.events.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.StompWebSocketEndpointRegistration;

public class WebSocketConfigurationTest {
    private WebSocketConfiguration config;

    @Before
    public void beforeEach() {
        config = new WebSocketConfiguration();
    }

    @Test
    public void testConfig() {
        Assert.assertNotNull(WebSocketConfiguration.class.getAnnotation(Configuration.class));
        Assert.assertNotNull(WebSocketConfiguration.class.getAnnotation(EnableWebSocketMessageBroker.class));
    }

    @Test
    public void testRegisterStompEndpoints() {
        StompWebSocketEndpointRegistration eventReg = Mockito.mock(StompWebSocketEndpointRegistration.class);
        StompWebSocketEndpointRegistration serverReg = Mockito.mock(StompWebSocketEndpointRegistration.class);
        StompEndpointRegistry registry = Mockito.mock(StompEndpointRegistry.class);
        Mockito.when(registry.addEndpoint("/event")).thenReturn(eventReg);
        Mockito.when(registry.addEndpoint("/server")).thenReturn(serverReg);
        config.registerStompEndpoints(registry);
        Mockito.verify(registry, Mockito.atLeastOnce()).addEndpoint("/event");
        Mockito.verify(registry, Mockito.atLeastOnce()).addEndpoint("/server");
        Mockito.verify(eventReg).withSockJS();
        Mockito.verify(serverReg).withSockJS();
    }

    @Test
    public void testConfigureMessageBroker() {
        MessageBrokerRegistry registry = Mockito.mock(MessageBrokerRegistry.class);
        config.configureMessageBroker(registry);
        Mockito.verify(registry).enableSimpleBroker("/server");
        Mockito.verify(registry).setApplicationDestinationPrefixes("/client");
    }
}
