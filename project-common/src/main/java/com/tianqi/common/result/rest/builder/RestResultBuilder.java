package com.tianqi.common.result.rest.builder;

import com.tianqi.common.result.rest.entity.RestResultEntity;
import com.tianqi.common.result.rest.entity.ResultEntity;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/10 15:32
 * @Description:
 */
public class RestResultBuilder<T> extends ResultBuilder<T, RestResultBuilder<T>> {
    private boolean isOk;
    private T data;

    public RestResultBuilder<T> isOk(boolean isOk) {
        this.isOk = isOk;
        return this;
    }

    public RestResultBuilder<T> withData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public ResultEntity<T> build() {
        super.withData(new RestResultEntity<>(this.isOk, this.data));
        return super.build();
    }
}
