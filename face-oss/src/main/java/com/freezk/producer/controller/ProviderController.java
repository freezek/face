package com.freezk.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.nacos.client.utils.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.ls.LSOutput;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/provider")
@Slf4j
public class ProviderController {
    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping(value = "/echo/{source}", method = RequestMethod.GET)
    @SentinelResource("provider")
    public String echo(@PathVariable String source) throws UnknownHostException {
        log.info(InetAddress.getLocalHost().getHostAddress().toString());
        return "Hello Nacos I'm server provider !" +source+"--" +new Random().nextInt(1000);
        //return restTemplate.getForObject("https://baidu.com", String.class);
    }
}
