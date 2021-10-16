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
 * TqAuthUserRoleGroupRelation表：用户角色组关联表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:35
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_user_role_group_relation")
public class TqAuthUserRoleGroupRelationDO extends BaseDO
        implements Serializable {
    private static final long serialVersionUID = 241222962301986881L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_group_id")
    private Integer roleGroupId;
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Integer userId;

}
