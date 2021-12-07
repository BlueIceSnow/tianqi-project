package com.tianqi.auth.pojo.dto.req;

import lombok.Data;

/**
 * 角色资源关系DTO
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/18 11:15:45
 */
@Data
public class RoleResourceRelationDTO {
    private String roleId;
    private String resIds;
    private String resIdsDeleted;
    private Integer tenantId;
    private Integer appId;
}
