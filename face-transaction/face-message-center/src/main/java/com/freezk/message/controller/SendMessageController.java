package com.freezk.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class SendMessageController {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    @GetMapping("/send/{name}")
    public String send(@PathVariable("name") String name){
        ListenableFuture test = kafkaTemplate.send("test", name, name);
        return name;
    }
}
