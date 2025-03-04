package com.example.kafka_stream.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @Bean
    public Consumer<Message<String>> process() {
        return message -> {
            log.info("Message received: {}", message.getPayload());
        };
    }
}
