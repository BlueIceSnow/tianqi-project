package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthApplication表：应用表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:06
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_application")
public class TqAuthApplicationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 459491086874120598L;
    /**
     * APP唯一标识
     */
    @TableField(value = "app_key")
    private String appKey;

}
