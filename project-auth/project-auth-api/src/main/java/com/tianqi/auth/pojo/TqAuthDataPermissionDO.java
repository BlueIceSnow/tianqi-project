package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthDataPermission表：数据权限表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:49
 */
@TableName(value = "tq_auth_data_permission")
@Data
public class TqAuthDataPermissionDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 507000241699750037L;
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
