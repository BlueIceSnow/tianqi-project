package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthOrgRoleRelation表：组织角色关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:15
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_org_role_relation")
public class TqAuthOrgRoleRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -92305064897245519L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 组织ID
     */
    @TableField(value = "org_id")
    private Integer orgId;

}
