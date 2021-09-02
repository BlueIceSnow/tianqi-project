package com.tianqi.common.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 角色列表
     */
    private List<String> roles;

    /**
     * 所属租户ID
     */
    private Integer tenantId;

    /**
     * 所属组织CODE
     */
    private String orgCode;

    /**
     * 所属应用
     */
    private String appKey;

    /**
     * JWT 唯一 ID（JWT ID）
     */
    private String jti;

    public JwtUserClaims(final Integer id, final String name, final String username,
                         final List<String> roles, final String jti,
                         final Integer tenantId,
                         final String orgCode, final String appKey) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.roles = roles;
        this.jti = jti;
        this.tenantId = tenantId;
        this.orgCode = orgCode;
        this.appKey = appKey;
    }
}
