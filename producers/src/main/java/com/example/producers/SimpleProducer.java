package com.example.producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

@Component
public class SimpleProducer {

    private final KafkaProducer<String, String> kafkaProducer;

    public SimpleProducer(final KafkaProducer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public static void main(String[] args) {
    }

}
