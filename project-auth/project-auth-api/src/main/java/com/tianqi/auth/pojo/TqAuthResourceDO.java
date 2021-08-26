package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthResource表：资源表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:50
 */
@TableName(value = "tq_auth_resource")
@Data
public class TqAuthResourceDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -74602067585168895L;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
