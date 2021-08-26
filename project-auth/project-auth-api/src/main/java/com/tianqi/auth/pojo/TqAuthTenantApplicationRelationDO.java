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
 * @since 2021-08-25 19:45:39
 */
@TableName(value = "tq_auth_tenant_application_relation")
@Data
public class TqAuthTenantApplicationRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 325327806665219249L;
    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Integer tenantId;
    /**
     * 应用ID
     */
    @TableField(value = "application_id")
    private Integer applicationId;
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
