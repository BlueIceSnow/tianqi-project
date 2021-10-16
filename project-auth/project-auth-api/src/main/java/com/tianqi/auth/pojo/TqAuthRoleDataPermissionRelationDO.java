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
 * TqAuthRoleDataPermissionRelation表：角色数据权限关联表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_role_data_permission_relation")
public class TqAuthRoleDataPermissionRelationDO extends BaseDO
        implements Serializable {
    private static final long serialVersionUID = 622519049033710289L;
    /**
     * 角色组ID
     */
    @TableField(value = "role_id")
    private Integer roleId;
    /**
     * 数据权限ID
     */
    @TableField(value = "data_permission_id")
    private Integer dataPermissionId;

}
