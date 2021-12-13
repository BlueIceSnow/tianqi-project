package com.tianqi.common.enums.database;

import com.tianqi.common.enums.BaseEnum;

/**
 * 远程调用状态
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/12/10 16:32:33
 */
public enum RpcStatusEnum implements DatabaseEnum<String, Integer> {
    /**
     * 成功
     */
    OK("OK", 1),
    /**
     * 失败
     */
    ERROR("ERROR", 0);
    public String name;
    public Integer value;

    RpcStatusEnum(final String name, final Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getKey() {
        return this.name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public BaseEnum<String, Integer>[] enumValues() {
        return RpcStatusEnum.values();
    }
}
