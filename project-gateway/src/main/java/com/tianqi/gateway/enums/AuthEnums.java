package com.tianqi.gateway.enums;

import com.tianqi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 11:07
 * @Description:
 */

@Getter
@AllArgsConstructor
public enum AuthEnums implements BaseEnum {

    AUTH_FAIL(401,"认证失败");
    ;
    private final int code;
    private final String msg;


    @Override
    public BaseEnum[] enumValues() {
            return AuthEnums.values();
    }
}
