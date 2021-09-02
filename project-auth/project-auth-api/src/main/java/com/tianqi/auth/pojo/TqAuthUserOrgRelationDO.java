package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthUserOrgRelation表：用户组织关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 15:26:34
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_user_org_relation")
public class TqAuthUserOrgRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 745404704924103091L;
    /**
     * 角色组ID
     */
    @TableField(value = "user_id")
    private Integer userId;
    /**
     * 组织ID
     */
    @TableField(value = "org_id")
    private Integer orgId;
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
