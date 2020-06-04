package com.freezk.message;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MessageCenterApplication.class})
public class MessageCenterApplicationTests {

    @Autowired
    private KafkaTemplate kafkaTemplate;
    @Test
    public void contextLoads() {

        for (int i = 0; i < 10; i++) {
            kafkaTemplate.send("test", "hello", "spring boot kafka"+i);

        }

    }

}
