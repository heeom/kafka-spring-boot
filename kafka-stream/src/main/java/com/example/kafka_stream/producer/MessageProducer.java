package com.example.kafka_stream.producer;

import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

@Component
public class MessageProducer {

    private final StreamBridge streamBridge;

    public MessageProducer(final StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }


    public void publish(String message) {
        streamBridge.send("supply-out-0",
                MessageBuilder.withPayload(message)
                        .setHeader(KafkaHeaders.KEY, "test".getBytes(StandardCharsets.UTF_8))
                        .build()
        );
    }
}
