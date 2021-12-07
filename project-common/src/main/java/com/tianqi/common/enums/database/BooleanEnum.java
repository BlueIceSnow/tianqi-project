package com.tianqi.common.enums.database;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tianqi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author yuantianqi
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum implements DatabaseEnum<Boolean, Integer> {
    /**
     * 真
     */
    TRUE(true, 1),
    /**
     * 假
     */
    FALSE(false, 0);
    @EnumValue
    public final boolean name;
    public final Integer value;


    @Override
    public Boolean getKey() {
        return name;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public BaseEnum<Boolean, Integer>[] enumValues() {
        return BooleanEnum.values();
    }
}
