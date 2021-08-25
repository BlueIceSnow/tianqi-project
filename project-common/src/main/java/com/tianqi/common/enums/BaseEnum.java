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
     * @return
     */
    Key getKey();

    /**
     * 获取消息信息
     *
     * @return
     */
    Value getValue();

    ;

    BaseEnum[] enumValues();
}
