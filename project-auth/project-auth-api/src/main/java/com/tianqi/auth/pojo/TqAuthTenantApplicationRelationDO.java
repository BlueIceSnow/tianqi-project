package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthTenantApplicationRelation表：租户应用关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:20
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_tenant_application_relation")
public class TqAuthTenantApplicationRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -80957153292099734L;
    /**
     * 租户Key
     */
    @TableField(value = "tenant_key")
    private String tenantKey;
    /**
     * 应用Key
     */
    @TableField(value = "application_key")
    private String applicationKey;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
