package com.tianqi.common.result.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author yuantianqi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResultEntity<T> implements RestEntity<T> {
    private boolean isOk;
    private T row;
}