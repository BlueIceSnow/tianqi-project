package com.tianqi.auth.pojo;

import com.alibaba.fastjson.JSONObject;
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
 * TqAuthRoleGroup表：角色组表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:31
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role_group")
public class TqAuthRoleGroupDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 967224027854004221L;
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
