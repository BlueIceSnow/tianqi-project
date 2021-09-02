package com.tianqi.auth.config.sql;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.tianqi.auth.util.SqlConditionUtil;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.schema.Column;

import java.util.Map;

/**
 * @Author: yuantianqi
 * @Date: 2021/9/2 09:51
 * @Description:
 */
public class OrgCodeHandler implements TenantLineHandler {
    @Override
    public Expression getTenantId() {
        for (final Map.Entry<String, String> entry : SqlConditionUtil
                .getOrgCodeCond().entrySet()) {
            final LikeExpression likeExpression = new LikeExpression();
            likeExpression.withLeftExpression(new Column(entry.getKey()));
            likeExpression.withRightExpression(new StringValue(entry.getValue() + "%"));
            return likeExpression;
        }
        return null;
    }

    @Override
    public String getTenantIdColumn() {
        return SqlConditionUtil.ORG_CODE;
    }

    @Override
    public boolean ignoreTable(final String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName) || SqlConditionUtil
                .getOrgCodeCond().size() == 0;
    }
}
