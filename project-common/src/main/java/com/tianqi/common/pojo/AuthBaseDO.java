package com.tianqi.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * 认证平台通过BaseDO
 *
 * @Author: yuantianqi
 * @Date: 2021/8/25 16:39
 * @Description:
 */
@Data
public class AuthBaseDO extends BaseDO implements Serializable {

    /**
     * 所属租户
     */
    @TableField(value = "tenant_key")
    private Integer tenantKey;
    /**
     * 所属租户
     */
    @TableField(value = "tenant_id")
    private Integer tenantId;
    /**
     * 所属租户
     */
    @TableField(value = "app_id")
    private Integer appId;

}
