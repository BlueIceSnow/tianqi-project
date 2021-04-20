package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.tianqi.common.enums.BaseEnum;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 18:08
 * @Description:
 */
public class CustomBeanDeSerializerModifier extends BeanDeserializerModifier {
    @Override
    public JsonDeserializer<?> modifyEnumDeserializer(DeserializationConfig config, JavaType type, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        return super.modifyEnumDeserializer(config, type, beanDesc, deserializer);
    }

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        if (beanDesc.getType().getRawClass().isAssignableFrom(BaseEnum.class)){
            return new CustomDeserializer.StatusDeSerializer();
        }
        return super.modifyDeserializer(config, beanDesc, deserializer);
    }
}
