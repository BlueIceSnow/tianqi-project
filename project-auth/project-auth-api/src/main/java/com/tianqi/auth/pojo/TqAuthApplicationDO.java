package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthApplication表：应用表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:35:40
 */
@TableName(value = "tq_auth_application")
@Data
public class TqAuthApplicationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 523405568059856529L;
    /**
     * APP唯一标识
     */
    @TableField(value = "app_key")
    private String appKey;

}
