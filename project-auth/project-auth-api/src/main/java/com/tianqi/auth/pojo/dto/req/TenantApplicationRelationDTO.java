package com.tianqi.auth.pojo.dto.req;

import lombok.Data;

/**
 * 租户应用关系DTO
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/16 10:23:46
 */
@Data
public class TenantApplicationRelationDTO {
    private String tenantId;
    private String applicationIds;
    private String applicationIdsDeleted;
}
