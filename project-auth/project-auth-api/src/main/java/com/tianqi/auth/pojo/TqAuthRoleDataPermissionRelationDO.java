package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthRoleDataPermissionRelation表：角色数据权限关联表
 *
 * @author yuantianqi
 * @since 2021-08-25 19:45:29
 */
@TableName(value = "tq_auth_role_data_permission_relation")
@Data
public class TqAuthRoleDataPermissionRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -99378827576033833L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 数据权限ID
     */
    @TableField(value = "data_permission_id")
    private Integer dataPermissionId;
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
