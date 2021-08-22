package com.tianqi.common.result.rest.entity;

import java.io.Serializable;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/11 14:57
 * @Description:
 */
public interface RestEntity<T> extends Serializable {
    /**
     * 获取业务数据
     *
     * @return
     */
    T doOrDto();
}
