package com.freezk.face.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
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
    //@SentinelResource("consumer")
    @RequestMapping(value = "/echo/{source}", method = RequestMethod.GET)
    public String echo(@PathVariable String source) {
        return restTemplate.getForObject("http://face-provider/provider/echo/"+source, String.class);
    }
}
