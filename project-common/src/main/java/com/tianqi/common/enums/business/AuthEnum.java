package com.tianqi.common.enums.business;

import com.tianqi.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: yuantianqi
 * @Date: 2021/4/22 11:07
 * @Description:
 */

@Getter
@AllArgsConstructor
public enum AuthEnum implements BusinessEnum<Integer, String> {

    /**
     * 认证失败
     */
    NOT_LOGIN(300003, "暂未登陆"),
    LOGIN_FAIL(300004, "登录失败"),
    AUTHORIZE_FAIL(400005, "无权访问"),
    LOGIN_SUCCESS(200001, "登录成功"),
    LOGOUT_SUCCESS(200002, "登出成功"),
    MENU_LOAD_OK(200003, "菜单加载成功"),
    LOGOUT_FAIL(300006, "登出失败");
    /**
     * 错误码
     */
    private final int code;
    /**
     * 错误信息
     */
    private final String msg;


    @Override
    public Integer getKey() {
        return this.code;
    }

    @Override
    public String getValue() {
        return this.msg;
    }

    @Override
    public BaseEnum<Integer, String>[] enumValues() {
        return AuthEnum.values();
    }
}
