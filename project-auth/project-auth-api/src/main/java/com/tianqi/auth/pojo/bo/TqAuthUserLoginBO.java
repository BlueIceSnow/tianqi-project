package com.tianqi.auth.pojo.bo;

import com.tianqi.auth.pojo.entity.JwtAuthority;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 业务层校验用户信息后的返回值
 *
 * @Author: yuantianqi
 * @Date: 2021/8/26 15:55
 * @Description:
 */
@Data
@Builder
public class TqAuthUserLoginBO {
    private Integer userId;
    private String name;
    private String username;
    private String password;
    private Integer tenantId;
    private String orgCode;
    private String appKey;
    private Integer appId;
    private List<JwtAuthority> authorities;
}
