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
    private Long id;
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
     * JWT 唯一 ID（JWT ID）
     */
    private String jti;

    public JwtUserClaims(Long id, String name, String username, List<String> roles,
                         String jti) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.roles = roles;
        this.jti = jti;
    }
}
