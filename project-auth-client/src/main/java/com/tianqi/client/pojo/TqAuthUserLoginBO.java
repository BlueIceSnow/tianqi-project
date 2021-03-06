package com.tianqi.client.pojo;


import com.tianqi.common.enums.database.BooleanEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 业务层校验用户信息后的返回值.
 *
 * @Author: yuantianqi
 * @Date: 2021/8/26 15:55
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TqAuthUserLoginBO {
    /**
     * 用户ID.
     */
    private Integer userId;
    /**
     * 用户姓名.
     */
    private String name;
    /**
     * 状态：0-禁用;1-启用
     */
    private BooleanEnum state;
    /**
     * 用户类型：A-管理员;T-租户;U-用户
     */
    private String type;
    /**
     * 用户名.
     */
    private String username;
    /**
     * 密码.
     */
    private String password;
    /**
     * 租户ID.
     */
    private Integer tenantId;
    /**
     * 组织CODE.
     */
    private String orgCode;
    /**
     * 组织ID.
     */
    private Integer orgId;
    /**
     * 所属应用.
     */
    private String appKey;
    /**
     * 所属应用ID.
     */
    private Integer appId;
    /**
     * 所拥有角色.
     */
    private List<JwtAuthority> authorities;
    /**
     * 所拥有数据权限.
     */
    private List<Integer> dataPermissions;
}
