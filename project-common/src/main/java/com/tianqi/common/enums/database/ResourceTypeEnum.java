package com.tianqi.common.enums.database;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tianqi.common.enums.BaseEnum;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 15:55
 * @Description:
 */
public enum ResourceTypeEnum implements DatabaseEnum<String, String> {
    /**
     * 链接资源
     */
    URL("链接", "U"),

    /**
     * 按钮资源
     */
    BUTTON("按钮", "B");


    private final String name;
    @EnumValue
    private final String value;

    ResourceTypeEnum(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public BaseEnum<String, String>[] enumValues() {
        return ResourceTypeEnum.values();
    }
}
