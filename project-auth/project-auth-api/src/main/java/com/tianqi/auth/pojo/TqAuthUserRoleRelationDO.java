package com.tianqi.auth.pojo;

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
 * TqAuthUserRoleRelation表：用户角色组关联表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:36
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_user_role_relation")
public class TqAuthUserRoleRelationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 376856665122476487L;
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "app_id")
    private Integer appId;

}
