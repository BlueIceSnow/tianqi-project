package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * User表：
 *
 * @author yuantianqi
 * @since 2021-04-11 17:33:36
 */
@TableName(value = "tq_user")
@Data
public class TqUserDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -99727166387931805L;

    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;


}
