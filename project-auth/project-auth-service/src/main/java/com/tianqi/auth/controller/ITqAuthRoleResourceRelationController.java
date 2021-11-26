package com.tianqi.auth.controller;

import com.tianqi.auth.pojo.TqAuthRoleResourceRelationDO;
import com.tianqi.auth.pojo.dto.req.RoleResourceRelationDTO;
import com.tianqi.common.controller.IBaseController;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 角色资源关联表(TqAuthRoleResourceRelation)表控制层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:27:18
 */
public interface ITqAuthRoleResourceRelationController
        extends IBaseController<TqAuthRoleResourceRelationDO> {

    /**
     * 根据角色ID及资源类型获取资源列表
     *
     * @param roleId 角色ID
     * @param type   资源类型
     * @return
     */
    @GetMapping("loadAuthorisedResByRoleId")
    ResultEntity<List<TqAuthRoleResourceRelationDO>> loadAuthorisedResByRoleId(String roleId,
                                                                               String type);

    /**
     * 授权资源给角色
     *
     * @param roleResourceRelationDTO 关联关系
     * @return
     */
    @PutMapping("authoriseResToRole")
    ResultEntity<Boolean> authoriseResToRole(
            @RequestBody RoleResourceRelationDTO roleResourceRelationDTO);
}
