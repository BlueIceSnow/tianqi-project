package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.enums.BooleanEnum;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthUser表：用户表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:52
 */
@TableName(value = "tq_auth_user")
@Data
public class TqAuthUserDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -87238848511299658L;
    /**
     * 用户类型：A-管理员;T-租户;U-用户
     */
    @TableField(value = "type")
    private String type;
    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @TableField(value = "password")
    private String password;
    /**
     * 状态：0-禁用;1-启用
     */
    @TableField(value = "state")
    private BooleanEnum state;
    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Integer tenantId;

}
