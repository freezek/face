package com.yixue.facegateway.config;

import com.yixue.facegateway.filter.AuthGatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;

public class AuthGatewayFilterFactory  extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new AuthGatewayFilter();
    }
}