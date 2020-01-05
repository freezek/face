package com.freezk.face.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public String echo() {
        return "Hello Nacos I'm server provider !";
    }
}