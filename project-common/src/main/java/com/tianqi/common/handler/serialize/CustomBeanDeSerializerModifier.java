package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.tianqi.common.enums.BaseEnum;

import java.util.function.Supplier;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 18:08
 * @Description:
 */
public class CustomBeanDeSerializerModifier extends BeanDeserializerModifier {
    @Override
    public JsonDeserializer<?> modifyEnumDeserializer(final DeserializationConfig config,
                                                      final JavaType type,
                                                      final BeanDescription beanDesc,
                                                      final JsonDeserializer<?> deserializer) {
        return customEnumDeserializer(beanDesc, () -> super.modifyEnumDeserializer(config,
                type, beanDesc, deserializer));
    }

    @Override
    public JsonDeserializer<?> modifyDeserializer(final DeserializationConfig config,
                                                  final BeanDescription beanDesc,
                                                  final JsonDeserializer<?> deserializer) {
        return customEnumDeserializer(beanDesc, () -> super.modifyDeserializer(config,
                beanDesc,
                deserializer));
    }

    public JsonDeserializer<?> customEnumDeserializer(final BeanDescription beanDescription,
                                                      final Supplier<JsonDeserializer<?>> supplier) {
        final boolean isBaseEnumTypeSubClass =
                beanDescription.getType().getRawClass().getInterfaces().length != 0 &&
                        beanDescription.getType().getRawClass().getInterfaces()[0]
                                .isAssignableFrom(BaseEnum.class);
        final boolean isBaseEnumType =
                beanDescription.getType().getRawClass().getInterfaces().length != 0 &&
                        beanDescription.getType().getRawClass().getInterfaces()[0]
                                .isAssignableFrom(BaseEnum.class);
        if (isBaseEnumTypeSubClass || isBaseEnumType) {
            return new CustomDeserializer.StatusDeSerializer();
        }
        return supplier.get();
    }
}
