package com.tianqi.common.enums;

import lombok.AllArgsConstructor;

/**
 * @Author yuantianqi
 */
@AllArgsConstructor
public enum SexEnum implements DatabaseEnum<Integer, String> {
    /**
     * 男
     */
    WOMAN(1, "女"),
    /**
     * 女
     */
    MAN(0, "男");
    public final Integer name;
    public final String value;


    @Override
    public Integer getKey() {
        return this.name;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public BaseEnum<Integer, String>[] enumValues() {
        return SexEnum.values();
    }
}
