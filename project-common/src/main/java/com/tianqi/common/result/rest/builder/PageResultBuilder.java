package com.tianqi.common.result.rest.builder;

import com.tianqi.common.result.rest.entity.PageResultEntity;
import com.tianqi.common.result.rest.entity.ResultEntity;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/10 15:32
 * @Description:
 */
public class PageResultBuilder<T> extends ResultBuilder<T, PageResultBuilder<T>> {
    private long total;
    private T rows;

    public PageResultBuilder<T> withTotal(long total) {
        this.total = total;
        return this;
    }

    public PageResultBuilder<T> withRows(T rows) {
        this.rows = rows;
        return this;
    }


    @Override
    public ResultEntity<T> build() {
        super.withData(new PageResultEntity<>(this.total, this.rows));
        return super.build();
    }
}
