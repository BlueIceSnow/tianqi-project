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
 * @since 2021-08-26 14:35:51
 */
@TableName(value = "tq_auth_role_group")
@Data
public class TqAuthRoleGroupDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -61049092055355436L;
    /**
     * 互斥角色ID列表
     */
    @TableField(value = "include_roles")
    private JSONObject includeRoles;
    /**
     * 租户ID
     */
    @TableField(value = "tenant_id")
    private Integer tenantId;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
