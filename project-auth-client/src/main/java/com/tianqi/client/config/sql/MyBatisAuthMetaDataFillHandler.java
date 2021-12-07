package com.tianqi.client.config.sql;

import com.alibaba.fastjson.JSONObject;
import com.tianqi.client.util.AuthUtil;
import com.tianqi.common.config.MyBatisMetaDataFillHandler;
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
public class MyBatisAuthMetaDataFillHandler extends MyBatisMetaDataFillHandler {
    private static final String EXT_FIELD_NAME = "extField";
    private static final String CREATE_USER = "createUser";
    private static final String UPDATE_USER = "updateUser";

    @Override
    public void insertFill(final MetaObject metaObject) {
        if (metaObject.getValue(EXT_FIELD_NAME) == null) {
            this.strictInsertFill(metaObject, EXT_FIELD_NAME, JSONObject.class, new JSONObject());

        }
        if (metaObject.getValue(CREATE_USER) == null) {
            this.strictInsertFill(metaObject, CREATE_USER, Integer.class,
                    AuthUtil.userClaims().getId());
        }
        if (metaObject.getValue(UPDATE_USER) == null) {
            this.strictUpdateFill(metaObject, UPDATE_USER, Integer.class,
                    AuthUtil.userClaims().getId());
        }
    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        if (metaObject.getValue(EXT_FIELD_NAME) == null) {
            this.strictUpdateFill(metaObject, EXT_FIELD_NAME, JSONObject.class, new JSONObject());
        }
        if (metaObject.getValue(UPDATE_USER) == null) {
            this.strictUpdateFill(metaObject, UPDATE_USER, Integer.class,
                    AuthUtil.userClaims().getId());
        }
    }
}
