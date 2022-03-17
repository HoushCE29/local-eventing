package dev.houshce29.example;

import dev.houshce29.events.EventPublisher;
import dev.houshce29.events.server.EventMessagingSystem;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EventMessagingSystem.class)
public class ExampleConfig {

    @Bean
    public ColorChangeEventListener colorChangeEventListener(EventPublisher publisher) {
        return new ColorChangeEventListener(publisher);
    }
}
