package com.tianqi.auth.pojo.dto.resp;

import com.tianqi.auth.pojo.TqAuthRoleDO;
import lombok.Data;

import java.util.List;

/**
 * 资源角色DTO
 *
 * @Author: yuantianqi
 * @Date: 2021/9/2 16:05
 * @Description:
 */
@Data
public class ResourceRoleDTO {
    /**
     * 路径.
     */
    private String url;
    /**
     * 租户ID.
     */
    private Integer tenantId;
    /**
     * 应用ID.
     */
    private Integer appId;
    /**
     * 角色.
     */
    private List<TqAuthRoleDO> roles;
}