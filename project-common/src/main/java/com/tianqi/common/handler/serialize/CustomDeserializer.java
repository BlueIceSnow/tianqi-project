package com.tianqi.common.handler.serialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.tianqi.common.enums.BaseEnum;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 18:39
 * @Description:
 */
public class CustomDeserializer {
    public static class StatusDeSerializer extends JsonDeserializer<BaseEnum> {
        @Override
        public BaseEnum deserialize(JsonParser jsonParser,
                                    DeserializationContext deserializationContext)
                throws IOException {
            jsonParser.nextFieldName();
            jsonParser.nextValue();
            String key = jsonParser.getValueAsString();
            jsonParser.nextFieldName();
            jsonParser.nextValue();
            String value = jsonParser.getValueAsString();
            jsonParser.nextFieldName();
            if (StringUtils.isEmpty(key) || StringUtils.isEmpty(value)) {
                return null;
            }
            ClassPathScanningCandidateComponentProvider provider =
                    new ClassPathScanningCandidateComponentProvider(false);
            provider.addIncludeFilter(new AssignableTypeFilter(BaseEnum.class));
            Set<BeanDefinition> components =
                    provider.findCandidateComponents("com/tianqi");
            for (BeanDefinition component : components) {
                try {
                    Class cls = Class.forName(component.getBeanClassName());
                    Object[] enumConstants = cls.getEnumConstants();
                    for (Object enumConstant : enumConstants) {
                        BaseEnum cast = BaseEnum.class.cast(enumConstant);
                        if (cast.getKey().toString().equals(key) &&
                                cast.getValue().toString().equals(value)) {
                            return cast;
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                // use class cls found
            }

            return null;
        }
    }
}
