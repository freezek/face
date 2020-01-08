package com.freezk.face.service.fallback;

import com.freezk.face.service.EchoService;
import org.springframework.web.bind.annotation.PathVariable;

public class EchoServiceFallback implements EchoService {
    @Override
    public String echo(@PathVariable("str") String str) {
        return "echo fallback";
    }
}
