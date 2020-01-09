package com.yixue.facegateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FaceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceGatewayApplication.class, args);
    }

}
