package com.tianqi.common.result.rest.entity;

import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/5 21:10
 * @Description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ResultEntity<T> {
    private StatusEnum status;
    private List<ValidateEntity> validates;
    private BaseException error;
    private RestEntity<T> data;
}