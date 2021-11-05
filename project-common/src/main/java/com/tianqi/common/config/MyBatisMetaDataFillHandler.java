package com.tianqi.common.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * MyBatis数据填充处理
 *
 * @Program: tianqi-project
 * @Author: ytq
 * @Date: 2021/11/05 10:40:40
 */
@Component
@Slf4j
public class MyBatisMetaDataFillHandler implements MetaObjectHandler {
    private static final String EXT_FIELD_NAME = "extField";

    @Override
    public void insertFill(final MetaObject metaObject) {
        if (metaObject.getValue(EXT_FIELD_NAME) == null) {
            this.strictInsertFill(metaObject, EXT_FIELD_NAME, JSONObject.class, new JSONObject());
        }
    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        if (metaObject.getValue(EXT_FIELD_NAME) == null) {
            this.strictUpdateFill(metaObject, EXT_FIELD_NAME, JSONObject.class, new JSONObject());
        }
    }
}
