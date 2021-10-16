package com.tianqi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author yuantianqi
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum implements BaseEnum<Integer, Boolean> {
    /**
     * 真
     */
    TRUE(1, true),
    /**
     * 假
     */
    FALSE(0, false);
    @EnumValue
    public final Integer name;
    public final boolean value;


    @Override
    public Integer getKey() {
        return name;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public BaseEnum<Integer, Boolean>[] enumValues() {
        return BooleanEnum.values();
    }
}
