package com.tianqi.common.controller;

import com.tianqi.common.pojo.BaseDO;
import com.tianqi.common.result.rest.entity.ResultEntity;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * controller层接口
 *
 * @author yuantianqi
 */
public interface IBaseController<DO extends BaseDO> {
    /**
     * 查询单个实体
     *
     * @param id 角色ID
     * @return
     */
    @GetMapping("{id}")
    ResultEntity<DO> getEntity(@PathVariable("id") String id);

    /**
     * 查询所有实体
     *
     * @param entity 请求参数
     * @return
     */
    @GetMapping
    ResultEntity<List<DO>> listEntity(DO entity);


    /**
     * 查询单个实体
     *
     * @param entity 请求参数
     * @param page   当前页，默认值1
     * @param size   每页大小，默认值10
     * @param
     * @return
     */
    @GetMapping("page")
    ResultEntity<DO> listPageEntity(DO entity
            , @RequestParam(value = "page", defaultValue = "1") int page
            , @RequestParam(value = "size", defaultValue = "10") int size);

    /**
     * 保存实体
     *
     * @param entity
     * @return
     */
    @PostMapping
    ResultEntity<DO> save(@RequestBody DO entity);

    /**
     * 更新实体
     *
     * @param entity
     * @return
     */
    @PutMapping
    ResultEntity<DO> update(@RequestBody DO entity);

    /**
     * 分页删除实体
     *
     * @param entity
     * @param page
     * @param id
     * @return
     */
    @Delete("page/{id}")
    ResultEntity<DO> removeByPage(DO entity
            , @RequestParam("page") int page, @RequestParam("size") int size, @PathVariable("id") String id);

    /**
     * 删除实体
     *
     * @param id
     * @param entity
     * @return
     */
    @Delete("{id}")
    ResultEntity<List<DO>> remove(DO entity, @PathVariable("id") String id);
}
