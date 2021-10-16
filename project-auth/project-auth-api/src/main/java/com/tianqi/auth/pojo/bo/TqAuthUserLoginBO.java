package com.tianqi.auth.pojo.bo;

import com.tianqi.auth.pojo.entity.JwtAuthority;
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
