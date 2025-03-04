package com.example.kafka_stream;

import com.example.kafka_stream.producer.MessageProducer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaStreamApplication implements ApplicationRunner {

	private final MessageProducer messageProducer;


	public KafkaStreamApplication(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}


	public static void main(String[] args) {
		SpringApplication.run(KafkaStreamApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		messageProducer.publish("hello zoey");
	}
}
