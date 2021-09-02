package com.tianqi.common.enums;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/11 13:20
 * @Description:
 */
public interface BaseEnum<Key, Value> {
    /**
     * 获取code码
     *
     * @return 获取枚举的标识Key
     */
    Key getKey();

    /**
     * 获取消息信息
     *
     * @return 获取枚举的标识Value
     */
    Value getValue();


    /**
     * 获取所有的枚举值
     *
     * @return 所有的枚举值
     */
    BaseEnum<Key, Value>[] enumValues();
}
