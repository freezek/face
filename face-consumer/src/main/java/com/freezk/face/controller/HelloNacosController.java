package com.freezk.face.controller;

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

    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echo() {
        return restTemplate.getForObject("http://face-provider/provider/echo/", String.class);
    }
}
