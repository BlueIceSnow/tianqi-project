package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * TqAuthOrg表：组织架构表
 *
 * @author yuantianqi
 * @since 2021-08-26 14:57:14
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName(value = "tq_auth_org")
public class TqAuthOrgDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = 220019693820520154L;
    /**
     * 应用ID
     */
    @TableField(value = "app_id")
    private Integer appId;

}
