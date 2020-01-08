package com.freezk.face.service;

import com.freezk.face.service.fallback.EchoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "face-provider",fallback = EchoServiceFallback.class, configuration = EchoService.FeignConfiguration.class)
public interface EchoService {

    @GetMapping(value = "/provider/echo/{str}")
    String echo(@PathVariable("str") String str);

    class FeignConfiguration {
        @Bean
        public EchoServiceFallback echoServiceFallback() {
            return new EchoServiceFallback();
        }
    }
}