package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.enums.database.BooleanEnum;
import com.tianqi.common.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TqAuthTenant表：租户表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:32
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_tenant")
@Accessors(chain = true)
public class TqAuthTenantDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -95455460441344902L;
    /**
     * 租户Key
     */
    @TableField(value = "tenant_key")
    private String tenantKey;
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
