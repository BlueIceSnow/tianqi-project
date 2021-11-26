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
        final Class<?>[] interfaces = beanDescription.getType().getRawClass().getInterfaces();
        // 获取类型上的接口
        if (interfaces.length != 0) {
            final Class<?> anInterface = interfaces[0];
            final boolean isBaseEnum = anInterface.isAssignableFrom(BaseEnum.class);
            if (!isBaseEnum) {
                // 如果不是，则查看是否在其上上级接口上
                final Class<?>[] superInterfaces = anInterface.getInterfaces();
                if (superInterfaces.length == 0) {
                    return supplier.get();
                }
                final Class<?> superAnInterface = superInterfaces[0];
                if (superAnInterface.isAssignableFrom(BaseEnum.class)) {
                    return new CustomDeserializer.StatusDeSerializer();
                }
            } else {
                return new CustomDeserializer.StatusDeSerializer();
            }
        }
        return supplier.get();
    }
}
