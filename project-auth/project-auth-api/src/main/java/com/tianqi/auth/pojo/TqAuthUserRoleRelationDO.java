package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthUserRoleRelation表：用户角色组关联表
 *
 * @author yuantianqi
 * @since 2021-08-31 11:08:00
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_user_role_relation")
public class TqAuthUserRoleRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 454444032455680220L;
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

}