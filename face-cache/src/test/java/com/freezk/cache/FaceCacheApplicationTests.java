package com.freezk.cache;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.freezk.cache.tools.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@SpringBootTest(classes = {FaceCacheApplication.class})
@RunWith(SpringRunner.class)
@Slf4j
public class FaceCacheApplicationTests {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void contextLoads() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        long l = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            UnknownError unknownError = new UnknownError();
            redisUtil.setEx(String.valueOf(i), objectMapper.writeValueAsString(unknownError),10000, TimeUnit.MILLISECONDS);
        }
        log.info("save to redis 1000 times spend time:{}",System.currentTimeMillis()-l);
        log.info("foo's value:{}",redisUtil.get("foo"));
//        for (int i = 0; i < 1000; i++) {
//            redisUtil.delete(String.valueOf(i));
//        }
    }

}
