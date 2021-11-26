package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * TqAuthApplication表：应用表
 *
 * @Author yuantianqi
 * @since 2021-09-02 16:50:19
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_application")
@Accessors(chain = true)
public class TqAuthApplicationDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 156123648711611510L;
    /**
     * APP唯一标识
     */
    @TableField(value = "app_key")
    private String appKey;

}
