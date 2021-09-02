package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthResource表：资源表
 *
 * @author yuantianqi
 * @since 2021-08-26 15:26:31
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_resource")
public class TqAuthResourceDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 502425418446798457L;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
