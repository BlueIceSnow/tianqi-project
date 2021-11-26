package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TqAuthRoleResourceRelation表：角色资源关联表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role_resource_relation")
public class TqAuthRoleResourceRelationDO extends BaseDO
        implements Serializable {
    private static final long serialVersionUID = 899930064634840516L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 组织ID
     */
    @TableField(value = "resource_id")
    private Integer resourceId;

    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Integer tenantId;
}
