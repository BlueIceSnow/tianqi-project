package com.tianqi.auth.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tianqi.common.pojo.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * TqAuthOrg表：组织架构表
 *
 * @author yuantianqi
 * @since 2021-08-25 19:45:19
 */
@TableName(value = "tq_auth_org")
@Data
public class TqAuthOrgDO extends BaseDO implements Serializable {
    private static final long serialVersionUID = -27667638084624831L;
    /**
     * 组织编码
     */
    @TableField(value = "org_code")
    private String orgCode;
    /**
     * 应用Key
     */
    @TableField(value = "app_key")
    private String appKey;

}
