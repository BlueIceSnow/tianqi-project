package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthRoleResourceRelation表：角色资源关联表
 *
 * @author yuantianqi
 * @since 2021-08-26 15:26:32
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role_resource_relation")
public class TqAuthRoleResourceRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -76576272678204547L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 组织ID
     */
    @TableField(value = "resource_id")
    private Integer resourceId;

}
