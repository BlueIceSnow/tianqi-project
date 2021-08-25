package com.tianqi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuantianqi
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
    public final Integer name;
    @EnumValue
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
