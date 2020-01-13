package com.yixue.gateway.config;

import com.yixue.gateway.filter.AuthGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

public class AuthGatewayFilterFactory  extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new AuthGatewayFilter();
    }
}