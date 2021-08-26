package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthUserRoleGroupRelation表：用户角色组关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:21
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_user_role_group_relation")
public class TqAuthUserRoleGroupRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 382310495076255294L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_group_id")
    private Integer roleGroupId;
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

}
