package com.yixue.gateway;

import com.yixue.gateway.auth.AuthUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationListener;

@SpringBootApplication
@EnableDiscoveryClient
public class FaceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(FaceGatewayApplication.class);
        springApplication.addListeners(new ApplicationListenerStarted());//增加监听器
        springApplication.run(args);
    }

    private static class ApplicationListenerStarted
            implements ApplicationListener<ApplicationStartedEvent> {
        @Override
        public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
            //权限初始化数据
            AuthUtil.init();
        }
    }
}
