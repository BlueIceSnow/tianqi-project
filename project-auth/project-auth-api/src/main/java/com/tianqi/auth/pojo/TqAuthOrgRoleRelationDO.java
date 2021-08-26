package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthOrgRoleRelation表：组织角色关联表
 *
 * @author yuantianqi
 * @since 2021-08-25 19:45:22
 */
@TableName(value = "tq_auth_org_role_relation")
@Data
public class TqAuthOrgRoleRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -63689965818239068L;
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
    /**
     * 应用Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
