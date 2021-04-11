package com.tianqi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author yuantianqi
 */
@AllArgsConstructor
@Getter
public enum SexEnum {
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
}
