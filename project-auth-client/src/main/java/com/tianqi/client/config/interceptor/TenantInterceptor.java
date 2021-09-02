package com.tianqi.client.config.interceptor;

import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.SQLException;

/**
 * @Author: yuantianqi
 * @Date: 2021/8/31 16:15
 * @Description:
 */
public class TenantInterceptor implements InnerInterceptor {
    @Override
    public void beforeQuery(final Executor executor, final MappedStatement ms,
                            final Object parameter,
                            final RowBounds rowBounds, final ResultHandler resultHandler,
                            final BoundSql boundSql) throws SQLException {
        InnerInterceptor.super
                .beforeQuery(executor, ms, parameter, rowBounds, resultHandler, boundSql);
    }


}
