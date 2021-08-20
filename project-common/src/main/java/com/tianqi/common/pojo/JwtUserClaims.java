package com.tianqi.common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/20 17:29
 * @Description:
 */
@Data
@AllArgsConstructor
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
     * 角色列表
     */
    private List<String> roles;

    /**
     * JWT 唯一 ID（JWT ID）
     */
    private String jti;
}
