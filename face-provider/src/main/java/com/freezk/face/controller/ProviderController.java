package com.freezk.face.controller;

import com.alibaba.nacos.client.utils.JSONUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/provider")
public class ProviderController {
    @RequestMapping(value = "/echo/{source}", method = RequestMethod.GET)
    public String echo(@PathVariable String source) {
        return "Hello Nacos I'm server provider !" +source+"--" +new Random().nextInt(1000);
    }
}
