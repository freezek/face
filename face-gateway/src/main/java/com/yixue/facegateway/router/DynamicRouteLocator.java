package com.yixue.facegateway.router;

import com.yixue.facegateway.filter.AuthGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamicRouteLocator {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/consumer/echo/{pram}")
                        .uri("lb://face-consumer")
                        .id("lb").filter(new AuthGatewayFilter())
                ).build();
    }
}
