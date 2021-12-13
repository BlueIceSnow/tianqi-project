package com.tianqi.common.handler.serialize;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tianqi.common.enums.BaseEnum;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 18:39
 * @Description:
 */
public class CustomDeserializer {
    public static class StatusDeSerializer extends JsonDeserializer<BaseEnum> {
        @Override
        public BaseEnum deserialize(final JsonParser jsonParser,
                                    final DeserializationContext deserializationContext)
                throws IOException {
            BaseEnum[] enumConstants = null;
            Class<?> keyClass = null;
            final JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.START_OBJECT) {
                jsonParser.nextToken();
                return null;
            }
            JsonStreamContext parseContext = jsonParser.getParsingContext();
            if (parseContext.getCurrentValue() == null) {
                parseContext = jsonParser.getParsingContext();
            }
            final Class<?> aClass = parseContext.getCurrentValue()
                    .getClass();
            try {
                final Field declaredField =
                        aClass.getDeclaredField(parseContext.getCurrentName());
                final Class<BaseEnum> type = (Class<BaseEnum>) declaredField.getType();
                enumConstants = type.getEnumConstants();
                keyClass = ((ParameterizedType) type.getGenericInterfaces()[0])
                        .getActualTypeArguments()[0].getClass();
            } catch (final NoSuchFieldException e) {
                try {
                    final Field declaredField = aClass.getSuperclass().getDeclaredField(
                            parseContext.getCurrentName());
                    final Class<BaseEnum> type = (Class<BaseEnum>) declaredField.getType();
                    enumConstants = type.getEnumConstants();
                    keyClass = ((ParameterizedType) type.getGenericInterfaces()[0])
                            .getActualTypeArguments()[0].getClass();
                } catch (final NoSuchFieldException noSuchFieldException) {
                    noSuchFieldException.printStackTrace();
                }
            }

            String key = "";

            switch (currentToken) {
                case VALUE_TRUE:
                case VALUE_FALSE:
                case VALUE_STRING:
                case VALUE_NUMBER_INT:
                case VALUE_EMBEDDED_OBJECT:
                    key = jsonParser.getValueAsString();
                    break;
                default:
                    key = null;

            }
            if (StrUtil.isNotBlank(key)) {
                for (final Object enumConstant : enumConstants) {
                    final BaseEnum cast = (BaseEnum) enumConstant;
                    if (cast.getKey().toString().equals(key)) {
                        return cast;
                    }
                }
            }
            return null;
        }
    }
}
