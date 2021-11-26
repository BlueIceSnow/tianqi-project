package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.exception.BaseException;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

/**
 * 自定义BeanSerializerModifier
 *
 * @Author yuantianqi
 */
public class CustomBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public JsonSerializer<?> modifyEnumSerializer(final SerializationConfig config,
                                                  final JavaType valueType,
                                                  final BeanDescription beanDesc,
                                                  final JsonSerializer<?> serializer) {
        final Class<?>[] interfaces = valueType.getRawClass().getInterfaces();
        // 获取类型上的接口
        if (interfaces.length != 0) {
            final Class<?> anInterface = interfaces[0];
            final boolean isBaseEnum = anInterface.isAssignableFrom(BaseEnum.class);
            if (!isBaseEnum) {
                // 如果不是，则查看是否在其上上级接口上
                final Class<?>[] superInterfaces = anInterface.getInterfaces();
                if (superInterfaces.length == 0) {
                    return super.modifyEnumSerializer(config, valueType, beanDesc, serializer);
                }
                final Class<?> superAnInterface = superInterfaces[0];
                if (superAnInterface.isAssignableFrom(BaseEnum.class)) {
                    return new CustomSerializer.StatusSerializer();
                }
            } else {
                return new CustomSerializer.StatusSerializer();
            }
        }
        return super.modifyEnumSerializer(config, valueType, beanDesc, serializer);
    }

    @Override
    public JsonSerializer<?> modifySerializer(final SerializationConfig config,
                                              final BeanDescription beanDesc,
                                              final JsonSerializer<?> serializer) {
        if (beanDesc.getType().getRawClass().equals(BaseException.class)) {
            return new CustomSerializer.BaseExceptionSerializer();
        }
        return super.modifySerializer(config, beanDesc, serializer);
    }

    @Override
    public List<BeanPropertyWriter> changeProperties(final SerializationConfig config,
                                                     final BeanDescription beanDesc,
                                                     final List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (int i = 0; i < beanProperties.size(); i++) {
            final BeanPropertyWriter writer = beanProperties.get(i);
            // 判断字段的类型，如果是数组或集合则注册nullSerializer
            if (isArrayType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(
                        new CustomNullJsonSerializer.NullArrayJsonSerializer());
            }
            if (isStringType(writer)) {
                writer.assignNullSerializer(
                        new CustomNullJsonSerializer.NullStringJsonSerializer());
            }
            if (isTimestamp(writer)) {
                writer.assignNullSerializer(
                        new CustomNullJsonSerializer.NullStringJsonSerializer());
            }
            if (isBooleanType(writer)) {
                writer.assignNullSerializer(
                        new CustomNullJsonSerializer.NullBooleanJsonSerializer());
            }
            if (isNumberType(writer)) {
                writer.assignNullSerializer(
                        new CustomNullJsonSerializer.NullNumberJsonSerializer());
            }
        }
        return beanProperties;
    }

    /**
     * 是否是数组
     */
    private boolean isArrayType(final BeanPropertyWriter writer) {
        final Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是String
     */
    private boolean isStringType(final BeanPropertyWriter writer) {
        final Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) ||
                Character.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是数值类型
     */
    private boolean isNumberType(final BeanPropertyWriter writer) {
        final Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是boolean
     */
    private boolean isBooleanType(final BeanPropertyWriter writer) {
        final Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }

    /**
     * 是否为Timestamp
     */
    private boolean isTimestamp(final BeanPropertyWriter writer) {
        final Class<?> rawClass = writer.getType().getRawClass();
        return rawClass.equals(Timestamp.class);
    }

}