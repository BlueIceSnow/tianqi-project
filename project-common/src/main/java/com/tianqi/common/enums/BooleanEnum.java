package com.tianqi.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuantianqi
 */
@Getter
@AllArgsConstructor
public enum BooleanEnum {
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
}
