package com.tianqi.common.config;

import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;

/**
 * RedisTemplate配置类
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/20 11:01:58
 */
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Object> redisTemplateObject(
            final RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        final RedisSerializer serializer = new RedisSerializer<Object>() {
            @Override
            public byte[] serialize(final Object o) throws SerializationException {
                return JSON.toJSONString(o).getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public Object deserialize(final byte[] bytes)
                    throws SerializationException {
                return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8));
            }
        };
        template.setHashKeySerializer(StringRedisSerializer.UTF_8);
        template.setHashValueSerializer(serializer);
        template.setKeySerializer(StringRedisSerializer.UTF_8);
        template.setValueSerializer(serializer);
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}
