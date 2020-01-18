package com.freezk.producer.cache.base;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
@PropertySource(value = {"redis.properties"})
public class BaseRedisConfig<K,V> {

    /*
     * 读取配置文件里的redis配置
     */
    @Value("${redis.cache.database}")
    private Integer cacheDatabaseIndex;

    //sessiondb的数据库索引
    @Value("${redis.session.database}")
    private Integer sessionDatabaseIndex;

    @Value("${redis.host}")
    private String hostName;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.lettuce.pool.max-idle}")
    private Integer maxIdle;

    @Value("${redis.lettuce.pool.min-idle}")
    private Integer minIdle;

    @Value("${redis.lettuce.pool.max-active}")
    private Integer maxActive;

    @Value("${redis.lettuce.pool.max-wait}")
    private Long maxWait;

    @Value("${redis.timeout}")
    private Long timeOut;

    @Value("${redis.lettuce.shutdown-timeout}")
    private Long shutdownTimeOut;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        //redis配置
        RedisConfiguration redisConfiguration = new
                RedisStandaloneConfiguration(hostName,port);
        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(cacheDatabaseIndex);
        ((RedisStandaloneConfiguration) redisConfiguration).setPassword(password);

        //连接池配置
        GenericObjectPoolConfig genericObjectPoolConfig =
                new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxIdle(maxIdle);
        genericObjectPoolConfig.setMinIdle(minIdle);
        genericObjectPoolConfig.setMaxTotal(maxActive);
        genericObjectPoolConfig.setMaxWaitMillis(maxWait);

        //redis客户端配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
                builder =  LettucePoolingClientConfiguration.builder().
                commandTimeout(Duration.ofMillis(timeOut));

        builder.shutdownTimeout(Duration.ofMillis(shutdownTimeOut));
        builder.poolConfig(genericObjectPoolConfig);
        LettuceClientConfiguration lettuceClientConfiguration = builder.build();

        //根据配置和客户端配置创建连接
        LettuceConnectionFactory lettuceConnectionFactory = new
                LettuceConnectionFactory(redisConfiguration,lettuceClientConfiguration);
        lettuceConnectionFactory .afterPropertiesSet();

        return lettuceConnectionFactory;
//        RedisConfiguration redisConfiguration = new RedisStandaloneConfiguration();
//        redisConfiguration.s
//        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("192.168.31.131", 6379));
    }
    @Bean
    public RedisTemplate<K,V> redisTemplate(){

        RedisTemplate<K,V> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        jackson2JsonRedisSerializer.setObjectMapper(om);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setKeySerializer(new StringRedisSerializer());
        return template;
    }
}
