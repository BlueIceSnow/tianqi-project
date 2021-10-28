package com.tianqi.client.config.sql;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.tianqi.common.util.ConditionUtil;
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
        for (final Map.Entry<String, String> entry : ConditionUtil
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
        return ConditionUtil.ORG_CODE;
    }

    @Override
    public boolean ignoreTable(final String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName) || ConditionUtil
                .getOrgCodeCond().size() == 0;
    }
}
