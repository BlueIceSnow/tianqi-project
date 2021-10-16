package com.tianqi.common.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianqi.common.dao.IBaseDAO;
import com.tianqi.common.enums.StatusEnum;
import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.rest.RestResult;
import com.tianqi.common.result.rest.entity.ResultEntity;
import com.tianqi.common.service.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 业务层抽象
 *
 * @Author yuantianqi
 */
@Transactional(rollbackFor = Exception.class)
public abstract class BaseServiceImpl<DAO extends IBaseDAO<DO>, DO extends BaseDO>
        implements IBaseService<DO> {
    protected DAO dao;

    @Autowired
    public void setDao(final DAO dao) {
        this.dao = dao;
    }

    /**
     * 获取实体
     *
     * @param id 请求参数
     * @return
     */
    @Override
    public ResultEntity<DO> getEntity(final String id) {
        final DO aDo = dao.selectById(id);
        return RestResult.<DO>builder()
                .withData(aDo)
                .isOk(true)
                .withStatus(StatusEnum.OK)
                .build();
    }

    /**
     * 根据参数查询实体
     *
     * @param entity 请求参数
     * @return
     */
    @Override
    public ResultEntity<List<DO>> listEntity(final DO entity) {
        final List<DO> entities = dao.selectList(new QueryWrapper<>(entity));
        return RestResult.<List<DO>>builder()
                .withData(entities)
                .withStatus(StatusEnum.OK)
                .isOk(true)
                .build();
    }

    /**
     * 分页查询实体
     *
     * @param entity 请求参数
     * @param page   当前页
     * @param size   每页大小
     * @return
     */
    @Override
    public ResultEntity<List<DO>> listPageEntity(final DO entity, final int page, final int size) {
        final Page<DO> result = dao.selectPage(Page.of(page, size), new QueryWrapper<>(entity));
        return RestResult.<List<DO>>builderPage()
                .withTotal(result.getTotal())
                .withRows(result.getRecords())
                .withStatus(StatusEnum.OK)
                .build();
    }

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    @Override
    public ResultEntity<DO> save(final DO entity) {
        final int i = dao.insert(entity);
        if (i != 0) {
            return RestResult.<DO>builder()
                    .withData(entity)
                    .isOk(true)
                    .withStatus(StatusEnum.OK)
                    .build();
        }
        return RestResult.<DO>builder()
                .withData(entity)
                .isOk(true)
                .withStatus(StatusEnum.OPTION_ERROR)
                .build();
    }

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    @Override
    public ResultEntity<DO> update(final DO entity) {

        final int i = dao.updateById(entity);
        if (i != 0) {
            return RestResult.<DO>builder()
                    .withData(entity)
                    .isOk(true)
                    .withStatus(StatusEnum.OK)
                    .build();
        }

        return RestResult.<DO>builder()
                .withData(entity)
                .isOk(true)
                .withStatus(StatusEnum.OPTION_ERROR)
                .build();
    }

    /**
     * 分页删除实体
     *
     * @param entity
     * @param page
     * @param size
     * @param id
     * @return
     */
    @Override
    public ResultEntity<List<DO>> removeByPage(final DO entity, final int page, final int size, final String id) {

        final int i = dao.deleteById(id);
        if (i != 0) {
            final ResultEntity<List<DO>> doPageResult = listPageEntity(entity, page, size);
            return doPageResult;
        }
        return RestResult.<List<DO>>builderPage()
                .withStatus(StatusEnum.DELETE_ERROR)
                .withTotal(0)
                .withRows(new ArrayList<>())
                .build();
    }

    /**
     * 删除实体通过ID
     *
     * @param id
     * @return
     */
    @Override
    public ResultEntity<List<DO>> remove(final DO entity, final String id) {
        final int i = dao.deleteById(id);
        if (i != 0) {
            final ResultEntity<List<DO>> listRestResult = listEntity(entity);
            return listRestResult;
        }
        return RestResult.<List<DO>>builder()
                .withStatus(StatusEnum.DELETE_ERROR)
                .isOk(false)
                .withData(new ArrayList<>())
                .build();
    }
}
