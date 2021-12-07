package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.enums.business.BusinessEnum;
import com.tianqi.common.enums.database.DatabaseEnum;
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
 * @Author yuantianqi
 */
@Slf4j
public class CustomSerializer {

    /**
     * status枚举序列化
     */
    public static class StatusSerializer extends JsonSerializer<BaseEnum> {
        @Override
        public void serialize(final BaseEnum status, final JsonGenerator jsonGenerator,
                              final SerializerProvider serializerProvider) throws IOException {
            final Field[] declaredFields = status.getClass().getDeclaredFields();
            final List<Field> fields = Arrays.asList(declaredFields).stream()
                    .filter(field -> !field.getType().getName().replaceAll("\\[L", "")
                            .replaceAll(";", "").equals(
                                    status.getClass().getName()))
                    .collect(Collectors.toList());

            if (status instanceof BusinessEnum) {
                jsonGenerator.writeStartObject();
                for (final Field field : fields) {
                    field.setAccessible(true);
                    try {
                        jsonGenerator.writeObjectField(field.getName(), field.get(status));
                    } catch (final Exception ex) {
                        if (log.isErrorEnabled()) {
                            log.error("serializer enum is fail");
                        }
                    }
                }
                jsonGenerator.writeEndObject();
            }
            if (status instanceof DatabaseEnum) {
                jsonGenerator.writeObject(status.getKey());
            }

        }
    }

    /**
     * baseException枚举序列化
     */
    public static class BaseExceptionSerializer extends JsonSerializer<BaseException> {
        @Override
        public void serialize(final BaseException baseException, final JsonGenerator jsonGenerator,
                              final SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("message",
                    baseException.getMessage() == null ? "" : baseException.getMessage());
            jsonGenerator.writeArrayFieldStart("stackTrace");
            if (baseException.getStackTrace() != null &&
                    baseException.getStackTrace().length != 0) {
                for (final StackTraceElement stackTraceElement : baseException
                        .getStackTrace()) {
                    jsonGenerator.writeObject(stackTraceElement);
                }
            }
            jsonGenerator.writeEndArray();
            jsonGenerator.writeEndObject();
        }
    }
}
