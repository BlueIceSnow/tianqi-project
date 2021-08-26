package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthRoleResourceRelation表：角色资源关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:51
 */
@TableName(value = "tq_auth_role_resource_relation")
@Data
public class TqAuthRoleResourceRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 185410307159588359L;
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

}
