package com.tianqi.auth.pojo.vo;

import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import com.tianqi.common.enums.BooleanEnum;
import lombok.Data;

import java.util.List;

/**
 * 用户登录成功后其信息，校验token返回的信息
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/10/21 14:25:20
 */
@Data
public class UserLoginInfoVO {
    /**
     * 用户ID.
     */
    private Integer id;
    /**
     * 用户类型：A-管理员;T-租户;U-用户
     */
    private String type;
    /**
     * 用户名
     */
    private String username;
    /**
     * 状态：0-禁用;1-启用
     */
    private BooleanEnum state;
    /**
     * 组织编码.
     */
    private String orgCode;
    /**
     * 组织ID.
     */
    private Integer orgId;
    /**
     * 租户ID
     */
    private Integer tenantId;

    /**
     * 用户菜单.
     */
    private List<ResourceDetailDTO> menus;
}
