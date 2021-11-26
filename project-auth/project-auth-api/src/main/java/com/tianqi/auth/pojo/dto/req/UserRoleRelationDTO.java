package com.tianqi.auth.pojo.dto.req;

import lombok.Data;

/**
 * 用户角色关系参数接收
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/25 16:31:55
 */
@Data
public class UserRoleRelationDTO {
    private Integer userId;
    private String roleIds;
    private String roleIdsDeleted;
    private Integer appId;
}
