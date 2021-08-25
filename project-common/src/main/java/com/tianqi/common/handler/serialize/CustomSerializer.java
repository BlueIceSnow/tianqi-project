package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.exception.BaseException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义序列化
 *
 * @author yuantianqi
 */
@Slf4j
public class CustomSerializer {

    /**
     * status枚举序列化
     */
    public static class StatusSerializer extends JsonSerializer<BaseEnum> {
        @Override
        public void serialize(BaseEnum status, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            final Field[] declaredFields = status.getClass().getDeclaredFields();
            final List<Field> fields = Arrays.asList(declaredFields).stream()
                    .filter(field -> !field.getType().getName().replaceAll("\\[L", "")
                            .replaceAll(";", "").equals(
                                    status.getClass().getName()))
                    .collect(Collectors.toList());

            jsonGenerator.writeStartObject();
            for (final Field field : fields) {
                field.setAccessible(true);
                try {
                    jsonGenerator.writeObjectField(field.getName(), field.get(status));
                } catch (Exception ex) {
                    if (log.isErrorEnabled()) {
                        log.error("serializer enum is fail");
                    }
                }
            }
            jsonGenerator.writeEndObject();
        }
    }

    /**
     * baseException枚举序列化
     */
    public static class BaseExceptionSerializer extends JsonSerializer<BaseException> {
        @Override
        public void serialize(BaseException baseException, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("message",
                    baseException.getMessage() == null ? "" : baseException.getMessage());
            jsonGenerator.writeArrayFieldStart("stackTrace");
            if (baseException.getStackTrace() != null &&
                    baseException.getStackTrace().length != 0) {
                for (StackTraceElement stackTraceElement : baseException
                        .getStackTrace()) {
                    jsonGenerator.writeObject(stackTraceElement);
                }
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }
}
