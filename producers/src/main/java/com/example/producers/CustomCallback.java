package com.example.producers;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(CustomCallback.class);
    private int seq;

    public CustomCallback(int seq) {
        this.seq = seq;
    }

    @Override
    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
        if (e == null) {
            logger.info("seq : {}, partition : {}, offset : {}", seq, recordMetadata.partition(), recordMetadata.offset());
        } else {
            logger.error("error ", e);
        }
    }
}
