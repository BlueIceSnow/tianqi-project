package com.tianqi.common.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.rest.entity.ResultEntity;

import java.util.List;

/**
 * 基础业务层抽象
 *
 * @Author yuantianqi
 */
public interface IBaseService<DO extends BaseDO> {
    /**
     * 查询单个实体
     *
     * @param id 请求参数
     * @return 查询到的实体
     */
    ResultEntity<DO> getEntity(String id);

    /**
     * 查询所有实体
     *
     * @param entity 请求参数
     * @return 查询到的实体列表
     */
    ResultEntity<List<DO>> listEntity(DO entity);


    /**
     * 查询单个实体
     *
     * @param entity 请求参数
     * @param page   当前页
     * @param size   每页大小
     * @return 分页查询实体列表
     */
    ResultEntity<List<DO>> listPageEntity(DO entity, int page, int size);

    /**
     * 保存实体
     *
     * @param entity 保存的参数
     * @return 保存后的实体数据
     */
    ResultEntity<DO> save(DO entity);

    /**
     * 更新实体
     *
     * @param entity 更新参数
     * @return 更新后的实体数据
     */
    ResultEntity<DO> update(DO entity);

    /**
     * 分页删除实体
     *
     * @param entity 条件参数
     * @param page   当前页
     * @param size   每页大小
     * @param id     ID
     * @return 分页删除后所处页数据
     */
    ResultEntity<List<DO>> removeByPage(DO entity, int page, int size,
                                        String id);

    /**
     * 删除实体
     *
     * @param id     实体ID
     * @param entity 条件参数
     * @return 删除后实体列表
     */
    ResultEntity<List<DO>> remove(DO entity, String id);

    /**
     * 批量删除
     *
     * @param ids    实体ID列表
     * @param entity 条件参数
     * @return 删除后实体列表
     */
    ResultEntity<List<DO>> batchRemove(DO entity, String ids);

    /**
     * 分页批量删除
     *
     * @param entity 查询条件
     * @param page   当前页
     * @param size   页大小
     * @param ids    删除的IDS
     * @return 删除后当前页实体列表
     */
    ResultEntity<List<DO>> batchRemoveByPage(DO entity, int page, int size,
                                             String ids);

    /**
     * 条件删除资源
     *
     * @param entity 删除条件
     * @return 删除数量
     */
    int removeByCondition(Wrapper<DO> entity);

    /**
     * 删除相关数据
     *
     * @param ids 删除实体IDS
     */
    default void removeRelationData(final String[] ids) {
    }
}
