package com.tianqi.common.result.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author yuantianqi
 */
@Data
@AllArgsConstructor
public class PageResultEntity<T> implements RestEntity<T>, Serializable {
    private long total;
    private T rows;

    @Override
    public T doOrDto() {
        return rows;
    }
}