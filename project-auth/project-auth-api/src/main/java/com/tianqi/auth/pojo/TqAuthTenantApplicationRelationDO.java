package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthTenantApplicationRelation表：租户应用关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:52
 */
@TableName(value = "tq_auth_tenant_application_relation")
@Data
public class TqAuthTenantApplicationRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -58839604726506068L;
    /**
     * 应用Key
     */
    @TableField(value = "application_key")
    private String applicationKey;
    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Integer tenantId;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
