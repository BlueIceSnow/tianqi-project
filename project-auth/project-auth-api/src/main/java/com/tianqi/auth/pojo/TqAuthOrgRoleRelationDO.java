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
 * TqAuthOrgRoleRelation表：组织角色关联表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_org_role_relation")
public class TqAuthOrgRoleRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 376365246599681505L;
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
