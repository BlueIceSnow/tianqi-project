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
 * @since 2021-08-26 14:35:51
 */
@TableName(value = "tq_auth_role_data_permission_relation")
@Data
public class TqAuthRoleDataPermissionRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -19850976179780173L;
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

}
