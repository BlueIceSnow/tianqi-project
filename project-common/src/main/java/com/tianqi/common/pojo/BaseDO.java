package com.tianqi.common.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.tianqi.common.enums.database.BooleanEnum;
import com.tianqi.common.handler.type.JsonObjectHandler;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 基础持久层
 *
 * @Author yuantianqi
 */
@Data
public class BaseDO implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 扩展字段
     */
    @TableField(value = "ext_field", fill = FieldFill.INSERT, typeHandler =
            JsonObjectHandler.class)
    private JSONObject extField;

    /**
     * 是否删除，逻辑删除
     */
    @TableField(value = "is_delete")
    @TableLogic(value = "0", delval = "1")
    private BooleanEnum deleted;
    /**
     * 创建用户
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Integer createUser;
    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Timestamp createTime;
    /**
     * 更新用户
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Integer updateUser;
    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Timestamp updateTime;

    /**
     * 排序
     */
    @TableField(value = "sequence")
    @OrderBy
    private Integer sequence;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
