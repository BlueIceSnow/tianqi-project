package com.tianqi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 11:07
 * @Description:
 */

@Getter
@AllArgsConstructor
public enum AuthEnums implements BaseEnum {

    /**
     * 认证失败
     */
    NOT_LOGIN(403, "暂未登陆"),
    LOGIN_FAIL(403, "登录失败"),
    AUTHORIZE_FAIL(403, "无权访问"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "登出成功"),
    LOGOUT_FAIL(200, "登出失败");

    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误信息
     */
    private final String msg;


    @Override
    public BaseEnum[] enumValues() {
        return AuthEnums.values();
    }
}
