package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthOrg表：组织架构表
 *
 * @author yuantianqi
 * @since 2021-08-26 15:26:29
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_org")
public class TqAuthOrgDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -21674893004724968L;
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
