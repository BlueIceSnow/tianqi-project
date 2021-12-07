package com.tianqi.common.pojo;

import com.tianqi.common.enums.database.BooleanEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/20 17:29
 * @Description:
 */
@Data
@NoArgsConstructor
public class JwtUserClaims {
    /**
     * 用户ID
     */
    private Integer id;
    /**
     * 用户昵称
     */
    private String name;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户类型.
     */
    private String type;
    /**
     * 账号状态.
     */
    private BooleanEnum state;
    /**
     * 角色列表
     */
    private List<String> roles;

    /**
     * 数据权限列表
     */
    private List<Integer> dataPermissions;

    /**
     * 所属租户ID
     */
    private Integer tenantId;

    /**
     * 所属组织CODE
     */
    private String orgCode;

    /**
     * 所属组织ID
     */
    private Integer orgId;

    /**
     * 所属应用 Key
     */
    private String appKey;

    /**
     * 所属应用 Id
     */
    private Integer appId;

    /**
     * JWT 唯一 ID（JWT ID）
     */
    private String jti;

    public JwtUserClaims(final Integer id, final String name, final String type,
                         final BooleanEnum state,
                         final String username,
                         final List<String> roles, final List<Integer> dataPermissions,
                         final String jti, final Integer tenantId,
                         final String orgCode, final Integer orgId, final String appKey,
                         final Integer appId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.state = state;
        this.username = username;
        this.roles = roles;
        this.jti = jti;
        this.tenantId = tenantId;
        this.orgCode = orgCode;
        this.appKey = appKey;
        this.appId = appId;
        this.orgId = orgId;
        this.dataPermissions = dataPermissions;
    }
}
