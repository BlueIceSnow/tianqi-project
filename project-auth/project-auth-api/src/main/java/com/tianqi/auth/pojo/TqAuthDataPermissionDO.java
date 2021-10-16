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
 * TqAuthDataPermission表：数据权限表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:24
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_data_permission")
public class TqAuthDataPermissionDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 622371721006113199L;
    /**
     * 表名称
     */
    @TableField(value = "table_name")
    private String tableName;
    /**
     * 字段名
     */
    @TableField(value = "field_name")
    private String fieldName;
    /**
     * 表达式
     */
    @TableField(value = "expression")
    private String expression;
    /**
     * 条件
     */
    @TableField(value = "condition")
    private String condition;
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
