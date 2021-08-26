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
 * @since 2021-08-25 19:45:24
 */
@TableName(value = "tq_auth_resource")
@Data
public class TqAuthResourceDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -56981782719877686L;
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
