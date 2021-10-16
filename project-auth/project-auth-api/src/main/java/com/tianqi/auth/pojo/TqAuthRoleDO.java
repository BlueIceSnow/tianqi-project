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
 * TqAuthRole表：角色表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:29
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role")
public class TqAuthRoleDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 821235477186138749L;
    /**
     * 角色编码
     */
    @TableField(value = "code")
    private String code;
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
