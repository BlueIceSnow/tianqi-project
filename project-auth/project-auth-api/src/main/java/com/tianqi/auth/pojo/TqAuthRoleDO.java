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
 * TqAuthRole表：角色表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:16
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role")
public class TqAuthRoleDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -73580281061807361L;
    /**
     * 互斥角色ID列表
     */
    @TableField(value = "mutual_exclusion_roles")
    private JSONObject mutualExclusionRoles;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
