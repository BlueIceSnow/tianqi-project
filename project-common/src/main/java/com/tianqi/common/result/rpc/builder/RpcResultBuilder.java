package com.tianqi.common.result.rpc.builder;

import com.tianqi.common.enums.BaseEnum;
import com.tianqi.common.result.rpc.entity.RpcResultEntity;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 21:55
 * @Description:
 */
public class RpcResultBuilder<T> {
    private BaseEnum status;
    private T result;

    public RpcResultBuilder<T> withStatus(BaseEnum status) {
        this.status = status;
        return this;
    }

    public RpcResultBuilder<T> withResult(T result) {
        this.result = result;
        return this;
    }

    public RpcResultEntity<T> build() {
        return new RpcResultEntity<>(status, result);
    }
}
