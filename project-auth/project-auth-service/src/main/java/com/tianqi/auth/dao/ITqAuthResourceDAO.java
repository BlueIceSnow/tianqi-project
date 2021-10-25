package com.tianqi.auth.dao;

import com.tianqi.auth.pojo.TqAuthResourceDO;
import com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO;
import com.tianqi.common.dao.IBaseDAO;

import java.util.List;

/**
 * 资源表(TqAuthResource)表数据库访问层
 *
 * @Author yuantianqi
 * @since 2021-08-25 19:26:45
 */
public interface ITqAuthResourceDAO extends IBaseDAO<TqAuthResourceDO> {

    /**
     * 当前登录用户菜单.
     *
     * @param: roles
     * @param: tenantId
     * @param: appId
     * @return: java.util.List<com.tianqi.auth.pojo.dto.resp.ResourceDetailDTO>
     * @date: 2021/10/21 09:16:25
     */
    List<ResourceDetailDTO> selectMenuResource(List<String> roles, Integer tenantId, Integer appId);
}

