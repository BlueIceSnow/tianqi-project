package com.tianqi.auth.config.sql;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.tianqi.common.util.ConditionUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;

import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 09:53
 * @Description:
 */
public class IsDeleteHandler implements TenantLineHandler {
    @Override
    public Expression getTenantId() {
        for (final Map.Entry<String, Integer> entry : ConditionUtil.getIsDeletedCond()
                .entrySet()) {
            return new EqualsTo(new Column(entry.getKey()),
                    new LongValue(entry.getValue()));
        }
        return null;
    }

    @Override
    public String getTenantIdColumn() {
        return ConditionUtil.IS_DELETE;
    }

    @Override
    public boolean ignoreTable(final String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName);
    }
}
