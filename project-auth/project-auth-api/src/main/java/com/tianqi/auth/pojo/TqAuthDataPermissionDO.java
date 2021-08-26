package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthDataPermission表：数据权限表
 *
 * @author yuantianqi
 * @since 2021-08-25 19:45:17
 */
@TableName(value = "tq_auth_data_permission")
@Data
public class TqAuthDataPermissionDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -28495201155543067L;
    /**
     * 租户Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
