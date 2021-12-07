package com.tianqi.common.enums.database;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.tianqi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户类型枚举
 *
 * @Author: yuantianqi
 * @Date: 2021/8/31 15:20
 * @Description:
 */
@AllArgsConstructor
@Getter
public enum UserTypeEnum implements DatabaseEnum<String, String> {

    /**
     * 系统管理员
     */
    ADMIN("管理员", "A"),
    /**
     * 租户管理者用户
     */
    TENANT("租户", "T"),
    /**
     * 普通用户
     */
    USER("用户", "U");
    private final String name;
    @EnumValue
    private final String value;

    @Override
    public String getKey() {
        return name;
    }

    @Override
    public BaseEnum<String, String>[] enumValues() {
        return UserTypeEnum.values();
    }
}
