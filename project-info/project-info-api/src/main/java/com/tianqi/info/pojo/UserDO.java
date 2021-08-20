package com.tianqi.info.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * User表：
 *
 * @author yuantianqi
 * @since 2021-04-11 18:37:06
 */
@TableName(value = "user")
@Data
public class UserDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -75691601687488824L;

    @TableField(value = "username")
    private String username;

}
