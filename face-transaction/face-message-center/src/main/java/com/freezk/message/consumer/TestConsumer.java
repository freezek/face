package com.freezk.message.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TestConsumer {

    @KafkaListener(id = "face",topics = "test",containerFactory = "face_container")
    public void onTestMessage(Acknowledgment ack, ConsumerRecord<String, String> record){

        log.info("the offset is:{}, the value is:{}",record.offset(),record.value());
        ack.acknowledge();
    }
}
