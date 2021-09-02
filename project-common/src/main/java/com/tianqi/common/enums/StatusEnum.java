package com.tianqi.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求结果
 *
 * @author yuantianqi
 */

@AllArgsConstructor
@Getter
public enum StatusEnum implements BaseEnum<Integer, String> {
    /**
     * 操作成功
     */
    OK(200, "成功！"),
    /**
     * 操作失败
     */
    OPTION_ERROR(401, "操作失败"),
    /**
     * 删除失败
     */
    DELETE_ERROR(402, "删除失败"),
    /**
     * 服务器错误
     */
    SERVER_ERROR(500, "服务器错误"),
    /**
     * 参数校验错误
     */
    VALIDATE_PARAM_ERROR(403, "参数校验存在问题"),
    /**
     * 参数解析错误
     */
    PARAM_PARSE_ERROR(404, "参数解析错误"),
    /**
     * 业务错误
     */
    BUS_ERROR(501, "业务错误！");
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
