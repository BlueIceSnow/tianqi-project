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
 * @since 2021-08-25 19:45:26
 */
@TableName(value = "tq_auth_role")
@Data
public class TqAuthRoleDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -82312757690491388L;
    /**
     * 互斥角色ID列表
     */
    @TableField(value = "mutual_exclusion_roles")
    private JSONObject mutualExclusionRoles;
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
