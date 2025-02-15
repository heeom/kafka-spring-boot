package com.example.producers;


import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleProducerAsync {

    private static final Logger logger = LoggerFactory.getLogger(SimpleProducerAsync.class);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "test-topic";

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello-word");

        // 비동기(Async) 기반으로 메세지 전송
        // send message
//        19:01:36.014 [kafka-producer-network-thread | producer-1] INFO com.example.producers.SimpleProducerAsync -- kafka-producer-network-thread | producer-1
        producer.send(record, (metadata, e) -> {
            logger.info(Thread.currentThread().getName());
            if (e == null) {
                logger.info("{} timestamp : {}", metadata.toString(), metadata.offset());
            } else {
                logger.error("error ", e);
            }

        });
//    });
//        producer.send(record, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata recordMetadata, Exception e) { // send thread
//                logger.info(Thread.currentThread().getName());
//                if (e == null) {
//                    logger.info("{} timestamp : {}", recordMetadata.toString(), recordMetadata.offset());
//                } else {
//                    logger.error("error ", e);
//                }
//            }
//        });

        try {
            Thread.sleep(3000);
        } catch (
                InterruptedException e) {
            e.printStackTrace();
        }
        producer.flush();
        producer.close();
    }

}
