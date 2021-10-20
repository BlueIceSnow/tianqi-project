package com.tianqi.client.config;

import com.alibaba.fastjson.JSON;
import com.tianqi.client.config.security.authorization.JwtConfigAttribute;
import com.tianqi.common.config.RedisConfig;
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
 * RedisTemplate模板序列化配置
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/20 15:24:11
 */
@Configuration
public class AuthRedisConfig extends RedisConfig {
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
