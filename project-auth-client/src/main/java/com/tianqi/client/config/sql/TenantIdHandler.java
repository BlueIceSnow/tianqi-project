package com.tianqi.client.config.sql;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.tianqi.common.util.ConditionUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.schema.Column;

import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 09:51
 * @Description:
 */
public class TenantIdHandler implements TenantLineHandler {
    @Override
    public Expression getTenantId() {
        final Map<String, Integer> tenantIdCond = ConditionUtil.getTenantIdCond();
        for (final Map.Entry<String, Integer> entry : tenantIdCond
                .entrySet()) {
            return new EqualsTo(new Column(entry.getKey()),
                    new LongValue(entry.getValue()));
        }
        return null;
    }

    @Override
    public String getTenantIdColumn() {
        return ConditionUtil.TENANT_ID;
    }

    @Override
    public boolean ignoreTable(final String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName) ||
                ConditionUtil.getTenantIdCond().size() == 0;
    }
}
