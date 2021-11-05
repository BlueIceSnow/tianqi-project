package com.tianqi.auth.config.sql;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.tianqi.common.util.ConditionUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;

import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 09:51
 * @Description:
 */
public class AppIdHandler implements TenantLineHandler {

    @Override
    public Expression getTenantId() {
        for (final Map.Entry<String, Integer> entry : ConditionUtil
                .getAppIdCond()
                .entrySet()) {
            return new LongValue(entry.getValue());
        }
        return null;
    }

    @Override
    public String getTenantIdColumn() {
        return ConditionUtil.APP_ID;
    }

    @Override
    public boolean ignoreTable(final String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName) ||
                ConditionUtil.getAppIdCond().size() == 0;
    }
}
