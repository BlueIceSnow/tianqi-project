package com.tianqi.auth.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthRoleGroup表：角色组表
 *
 * @author yuantianqi
 * @since 2021-08-25 19:45:31
 */
@TableName(value = "tq_auth_role_group")
@Data
public class TqAuthRoleGroupDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 661338977802697741L;
    /**
     * 互斥角色ID列表
     */
    @TableField(value = "include_roles")
    private JSONObject includeRoles;
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
