package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthOrg表：组织架构表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:49
 */
@TableName(value = "tq_auth_org")
@Data
public class TqAuthOrgDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 337795242626334746L;
    /**
     * 组织编码
     */
    @TableField(value = "org_code")
    private String orgCode;
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
