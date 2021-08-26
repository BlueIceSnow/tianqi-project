package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthUserRoleGroupRelation表：用户角色组关联表
 *
 * @author yuantianqi
 * @since 2021-08-25 19:45:43
 */
@TableName(value = "tq_auth_user_role_group_relation")
@Data
public class TqAuthUserRoleGroupRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -41951223303286758L;
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
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
