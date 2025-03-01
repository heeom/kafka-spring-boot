package com.example.producers;


import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SimpleProducerAsyncWithKey {

    private static final Logger logger = LoggerFactory.getLogger(SimpleProducerAsyncWithKey.class);

    public static void main(String[] args) {

        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "0");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "multipart-topic";

        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, String.valueOf(i), "hello-word" + i);
            Callback customCallback = new CustomCallback(i);
            producer.send(record, customCallback);
        }

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
