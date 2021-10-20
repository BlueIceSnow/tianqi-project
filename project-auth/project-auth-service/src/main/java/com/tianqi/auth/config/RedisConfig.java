package com.tianqi.auth.config;

import com.alibaba.fastjson.JSON;
import com.tianqi.auth.config.security.authorization.JwtConfigAttribute;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Redis模板
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/20 15:41:02
 */
@Configuration
public class RedisConfig extends com.tianqi.common.config.RedisConfig {
    @Bean
    public RedisTemplate<String, List<JwtConfigAttribute>> redisTemplate(
            final RedisConnectionFactory redisConnectionFactory) {
        final RedisTemplate<String, List<JwtConfigAttribute>> template = new RedisTemplate<>();
        final RedisSerializer serializer = new RedisSerializer<List<JwtConfigAttribute>>() {
            @Override
            public byte[] serialize(final List<JwtConfigAttribute> o)
                    throws SerializationException {
                return JSON.toJSONString(o).getBytes(StandardCharsets.UTF_8);
            }

            @Override
            public List<JwtConfigAttribute> deserialize(final byte[] bytes)
                    throws SerializationException {
                return JSON.parseArray(new String(bytes, StandardCharsets.UTF_8),
                        JwtConfigAttribute.class);
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
