package com.yixue.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nacos")
public class TestController {

    @Value("${redis.host:111}")
    private String host;

    @GetMapping("/host")
    public String getHost(){
        return  host;
    }
}
