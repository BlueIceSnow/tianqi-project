package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.enums.BooleanEnum;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TqAuthTenant表：租户表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:52
 */
@TableName(value = "tq_auth_tenant")
@Data
public class TqAuthTenantDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -59910346201991512L;
    /**
     * 管理者用户ID
     */
    @TableField(value = "mgr_id")
    private Integer mgrId;
    /**
     * 生效时间
     */
    @TableField(value = "effect_time")
    private Timestamp effectTime;
    /**
     * 过期时间
     */
    @TableField(value = "expiry_time")
    private Timestamp expiryTime;
    /**
     * 状态：0-禁用;1-启用
     */
    @TableField(value = "state")
    private BooleanEnum state;

}
