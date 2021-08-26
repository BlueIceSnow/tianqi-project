package com.tianqi.auth.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthRoleGroup表：角色组表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:18
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role_group")
public class TqAuthRoleGroupDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 582890462664120750L;
    /**
     * 互斥角色ID列表
     */
    @TableField(value = "include_roles")
    private JSONObject includeRoles;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
