package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.enums.BooleanEnum;
import com.tianqi.common.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TqAuthResource表：资源表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_resource")
public class TqAuthResourceDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 661381537264201321L;
    /**
     * 资源URL
     */
    @TableField(value = "url")
    private String url;
    /**
     * 资源类型：U-url  B- button
     */
    @TableField(value = "type")
    private String type;
    /**
     * 上级资源
     */
    @TableField(value = "parent_id")
    private Integer parentId;
    /**
     * 图标
     */
    @TableField(value = "icon")
    private String icon;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

    /**
     * 组件路径
     */
    @TableField(value = "component")
    private String component;

    /**
     * 可关闭：0-不可关闭;1-可关闭
     */
    @TableField(value = "closeable")
    private BooleanEnum closeable;

}
