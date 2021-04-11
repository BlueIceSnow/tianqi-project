package com.tianqi.common.result.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author yuantianqi
 */
@Data
@AllArgsConstructor
public class PageResultEntity<T> implements RestEntity<T> {
    private long total;
    private List<T> rows;
}