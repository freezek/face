package com.freezk.controller;

import com.freezk.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/consumer")
public class HelloNacosController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EchoService echoService;
    //@SentinelResource("consumer")
    @RequestMapping(value = "/echo/{source}", method = RequestMethod.GET)
    public String echo(@PathVariable String source) {
        return echoService.echo(source);
    }
}
