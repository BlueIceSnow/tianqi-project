package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.exception.BaseException;

import java.io.IOException;

/**
 * 自定义序列化
 *
 * @author yuantianqi
 */
public class CustomSerializer {

    /**
     * status枚举序列化
     */
    public static class StatusSerializer extends JsonSerializer<BaseEnum> {
        @Override
        public void serialize(BaseEnum status, JsonGenerator jsonGenerator,
                              SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("code", status.getCode());
            jsonGenerator.writeStringField("message", status.getMsg());
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
