package com.yixue.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.yixue.gateway.auth.AuthUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

public class AuthGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        //获取header的参数
        String name = exchange.getRequest().getHeaders().getFirst("name");
        String password = exchange.getRequest().getHeaders().getFirst("password");
        boolean permitted = AuthUtil.isPermitted(name, password);//权限比较
        if (permitted) {
            return chain.filter(exchange);
        }
        else {
            ServerHttpResponse response = exchange.getResponse();
            JSONObject message = new JSONObject();
            message.put("status", -1);
            message.put("data", "鉴权失败");
            byte[] bits = message.toJSONString().getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(bits);
            response.setStatusCode(HttpStatus.OK);
            //指定编码，否则在浏览器中会中文乱码
            response.getHeaders().add("Content-Type", "text/plain;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
    }

    @Override
    public int getOrder()
    {
        return 10;
    }
}