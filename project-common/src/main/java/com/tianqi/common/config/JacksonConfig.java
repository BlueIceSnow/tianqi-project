package com.tianqi.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.tianqi.common.handler.serialize.CustomBeanDeSerializerModifier;
import com.tianqi.common.handler.serialize.CustomBeanSerializerModifier;
import com.tianqi.common.handler.serialize.CustomNullJsonSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 * 配置Jackson实体
 *
 * @Author yuantianqi
 */
@Configuration
public class JacksonConfig {
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper jacksonObjectMapper(
            final Jackson2ObjectMapperBuilder builder) {
        final ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        final SimpleModule simpleModule = new SimpleModule();
        simpleModule.setSerializerModifier(new CustomBeanSerializerModifier());
        simpleModule
                .setDeserializerModifier(new CustomBeanDeSerializerModifier());
        objectMapper.registerModule(simpleModule);
        final SerializerProvider serializerProvider =
                objectMapper.getSerializerProvider();
        serializerProvider.setNullValueSerializer(
                new CustomNullJsonSerializer.NullObjectJsonSerializer());
        return objectMapper;
    }
}