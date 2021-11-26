package com.tianqi.auth.pojo.dto.req;

import lombok.Data;

/**
 * 角色组织关联关系入参
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/22 11:29:04
 */
@Data
public class RoleGroupOrgRelationDTO {
    private Integer orgId;
    private String roleGroupIds;
    private String roleGroupIdsDeleted;
}
