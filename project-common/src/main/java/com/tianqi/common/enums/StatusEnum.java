package com.tianqi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求结果
 *
 * @Author yuantianqi
 */

@AllArgsConstructor
@Getter
public enum StatusEnum implements BaseEnum<Integer, String> {
    /**
     * 参数校验问题
     */
    VALIDATE_PARAM_ERROR(100000, "参数校验存在问题"),
    /**
     * 参数解析错误
     */
    PARAM_PARSE_ERROR(100002, "参数解析错误"),
    /**
     * 操作成功
     */
    OK(200000, "成功！"),

    /**
     * 操作失败
     */
    OPERATION_ERROR(500000, "操作失败"),

    /**
     * 删除失败
     */
    DELETE_ERROR(500001, "删除失败"),

    /**
     * 服务器错误
     */
    SERVER_ERROR(500002, "服务器错误");

    private final int code;
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
        return StatusEnum.values();
    }
}
