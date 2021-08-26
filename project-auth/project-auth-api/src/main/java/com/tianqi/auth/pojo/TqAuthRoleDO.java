package com.tianqi.auth.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthRole表：角色表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:50
 */
@TableName(value = "tq_auth_role")
@Data
public class TqAuthRoleDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -80286828731485921L;
    /**
     * 互斥角色ID列表
     */
    @TableField(value = "mutual_exclusion_roles")
    private JSONObject mutualExclusionRoles;
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
