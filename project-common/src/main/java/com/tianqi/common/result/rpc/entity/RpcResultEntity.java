package com.tianqi.common.result.rpc.entity;

import com.tianqi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/18 21:53
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcResultEntity<T> {
    private BaseEnum status;
    private T result;
}