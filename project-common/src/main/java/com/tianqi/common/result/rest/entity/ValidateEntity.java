package com.tianqi.common.result.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author yuantianqi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidateEntity implements Serializable {
    private String fields;
    private String message;
}
