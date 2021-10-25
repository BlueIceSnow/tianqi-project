package com.tianqi.common.result.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author yuantianqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResultEntity<T> implements RestEntity<T>, Serializable {
    private boolean ok;
    private T row;

    @Override
    public T doOrDto() {
        return row;
    }
}