package com.tianqi.common.result.rest.builder;

import com.tianqi.common.result.rest.entity.RestResultEntity;
import com.tianqi.common.result.rest.entity.ResultEntity;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/10 15:32
 * @Description:
 */
public class RestResultBuilder<T> extends ResultBuilder<T, RestResultBuilder<T>> {
    private boolean ok;
    private T data;

    public RestResultBuilder<T> ok(final boolean ok) {
        this.ok = ok;
        return this;
    }

    public RestResultBuilder<T> withData(final T data) {
        this.data = data;
        return this;
    }

    @Override
    public ResultEntity<T> build() {
        super.withData(new RestResultEntity<>(this.ok, this.data));
        return super.build();
    }
}
