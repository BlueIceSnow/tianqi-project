package com.tianqi.auth.config.security.interceptor;

import com.baomidou.mybatisplus.extension.parser.JsqlParserSupport;
import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/31 18:55
 * @Description:
 */
public class AuthInterceptor extends JsqlParserSupport implements InnerInterceptor {
    /*@Override
    public void beforeQuery(final Executor executor, final MappedStatement ms,
                            final Object parameter,
                            final RowBounds rowBounds, final ResultHandler resultHandler,
                            final BoundSql boundSql) throws SQLException {
        if (SqlConditionUtil.getOrgCodeCond().size() == 0 &&
                SqlConditionUtil.getTenantIdCond().size() == 0 &&
                SqlConditionUtil.getAppIdCond().size() == 0) {
            return;
        }
        final PluginUtils.MPBoundSql mpBs = PluginUtils.mpBoundSql(boundSql);
        mpBs.sql(parserSingle(mpBs.sql(), null));
    }

    @Override
    public void beforePrepare(final StatementHandler sh, final Connection connection,
                              final Integer transactionTimeout) {
        if (SqlConditionUtil.getOrgCodeCond().size() == 0 &&
                SqlConditionUtil.getTenantIdCond().size() == 0 &&
                SqlConditionUtil.getAppIdCond().size() == 0) {
            return;
        }
        final PluginUtils.MPStatementHandler mpSh = PluginUtils.mpStatementHandler(sh);
        final MappedStatement ms = mpSh.mappedStatement();
        final SqlCommandType sct = ms.getSqlCommandType();
        if (sct == SqlCommandType.INSERT || sct == SqlCommandType.UPDATE ||
                sct == SqlCommandType.DELETE) {
            if (InterceptorIgnoreHelper.willIgnoreIllegalSql(ms.getId())) {
                return;
            }
            final PluginUtils.MPBoundSql mpBs = mpSh.mPBoundSql();
            mpBs.sql(parserMulti(mpBs.sql(), null));
        }
    }

    @Override
    protected void processInsert(final Insert insert, final int index, final String sql,
                                 final Object obj) {
        super.processInsert(insert, index, sql, obj);
    }

    @Override
    protected void processDelete(final Delete delete, final int index, final String sql,
                                 final Object obj) {
        super.processDelete(delete, index, sql, obj);
    }

    @Override
    protected void processUpdate(final Update update, final int index, final String sql,
                                 final Object obj) {
        super.processUpdate(update, index, sql, obj);
    }

    *//**
     * 处理 Select 语句
     *
     * @param select
     * @param index
     * @param sql
     * @param obj
     *//*
    @Override
    protected void processSelect(final Select select, final int index, final String sql,
                                 final Object obj) {
        processSelectBody(select.getSelectBody());
        final List<WithItem> withItemsList = select.getWithItemsList();
        if (!CollectionUtils.isEmpty(withItemsList)) {
            withItemsList.forEach(this::processSelectBody);
        }
    }

    *//**
     * 处理 Select 体
     *
     * @param selectBody
     *//*
    protected void processSelectBody(final SelectBody selectBody) {
        if (selectBody == null) {
            return;
        }
        // 判断是否为简单 SQL，不是简单 SQL 则继续分解
        if (selectBody instanceof PlainSelect) {
            processPlainSelect((PlainSelect) selectBody);
        } else if (selectBody instanceof WithItem) {
            final WithItem withItem = (WithItem) selectBody;
            processSelectBody(withItem.getSelectBody());
        } else {
            final SetOperationList operationList = (SetOperationList) selectBody;
            final List<SelectBody> selectBodies = operationList.getSelects();
            if (CollectionUtils.isNotEmpty(selectBodies)) {
                selectBodies.forEach(this::processSelectBody);
            }
        }
    }

    *//**
     * 处理 PlainSelect
     *
     * @param plainSelect 简单 Select 对象
     *//*
    protected void processPlainSelect(final PlainSelect plainSelect) {
        final List<SelectItem> selectItems = plainSelect.getSelectItems();
        if (CollectionUtils.isNotEmpty(selectItems)) {
            selectItems.forEach(this::processSelectItem);
        }
    }

    private void buildWhereCond(final PlainSelect plainSelect) {
        final Expression where = plainSelect.getWhere();
        final Set<Map.Entry<String, Integer>> tenantIdEntries =
                SqlConditionUtil.getTenantIdCond().entrySet();
        final Set<Map.Entry<String, Integer>> appIdEntries =
                SqlConditionUtil.getAppIdCond().entrySet();
        final Set<Map.Entry<String, String>> orgCodeEntries =
                SqlConditionUtil.getOrgCodeCond().entrySet();
        final AndExpression rootAndExpression = new AndExpression();
        rootAndExpression.withLeftExpression(where);
        EqualsTo tenantIdEqualsTo = null;
        for (final Map.Entry<String, Integer> entry : tenantIdEntries) {
            tenantIdEqualsTo = new EqualsTo();
            tenantIdEqualsTo.withLeftExpression(new Column(entry.getKey()));
            tenantIdEqualsTo.withRightExpression(new LongValue(entry.getValue()));
        }
        if (tenantIdEqualsTo != null) {
            rootAndExpression.withRightExpression(tenantIdEqualsTo);
        }
        EqualsTo appIdEqualsTo = null;
        for (final Map.Entry<String, Integer> entry : appIdEntries) {
            appIdEqualsTo = new EqualsTo();
            appIdEqualsTo.withLeftExpression(new Column(entry.getKey()));
            appIdEqualsTo.withRightExpression(new LongValue(entry.getValue()));
        }
        addCondToRootExpression(rootAndExpression, appIdEqualsTo);
        LikeExpression orgCodeLike = null;
        for (final Map.Entry<String, String> entry : orgCodeEntries) {
            orgCodeLike = new LikeExpression();
            orgCodeLike.withLeftExpression(new Column(entry.getKey()));
            orgCodeLike.withRightExpression(new StringValue(entry.getValue() + "%"));
        }
        addCondToRootExpression(rootAndExpression, orgCodeLike);
        if (where == null) {
            plainSelect.setWhere(rootAndExpression.getRightExpression());
            return;
        }
        if (where instanceof OrExpression) {
            rootAndExpression.withLeftExpression(
                    new Parenthesis(rootAndExpression.getLeftExpression()));
        }
        plainSelect.setWhere(rootAndExpression);
    }

    *//**
     * 添加条件到 And 表达式
     *
     * @param rootAndExpression 根表达式
     * @param expression        传入表达式
     *//*
    private void addCondToRootExpression(final AndExpression rootAndExpression,
                                         final Expression expression) {
        if (rootAndExpression.getRightExpression() == null &&
                expression != null) {
            rootAndExpression.withRightExpression(expression);
        } else if (expression != null) {
            final Expression rightExpression =
                    rootAndExpression.getRightExpression();
            final AndExpression andExpression =
                    new AndExpression(rightExpression, expression);
            rootAndExpression.withRightExpression(andExpression);
        }
    }

    *//**
     * 处理查询列表
     *
     * @param selectItem 查询列表
     *//*
    protected void processSelectItem(final SelectItem selectItem) {
        if (selectItem instanceof SelectExpressionItem) {
            final SelectExpressionItem selectExpressionItem =
                    (SelectExpressionItem) selectItem;
            if (selectExpressionItem.getExpression() instanceof SubSelect) {
                // 如果是 SubSelect，则处理 Select 体
                processSelectBody(((SubSelect) selectExpressionItem.getExpression())
                        .getSelectBody());
            } else if (selectExpressionItem.getExpression() instanceof Function) {
                // 如果未 SQL 函数，则处理函数
                processFunction((Function) selectExpressionItem.getExpression());
            }
        }
    }

    */

    /**
     * 处理 SQL 函数的子查询
     *
     * @param function 函数
     *//*
    protected void processFunction(final Function function) {
        final ExpressionList parameters = function.getParameters();
        if (parameters != null) {
            // 根据取出来的表达式列表，逐层剖析递归，直到全部作为 SubSelect 处理完
            parameters.getExpressions().forEach(expression -> {
                if (expression instanceof SubSelect) {
                    processSelectBody(((SubSelect) expression).getSelectBody());
                } else if (expression instanceof Function) {
                    processFunction((Function) expression);
                }
            });
        }
    }*/
}
