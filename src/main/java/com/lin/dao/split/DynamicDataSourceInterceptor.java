package com.lin.dao.split;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Executable;
import java.util.Locale;
import java.util.Properties;

/**
 * @author lkmc2
 * @date 2018/5/22.
 * Mybatis对应的动态数据库拦截器
 */

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {

    /*** 日志记录器 ***/
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    /*** 判断是否以insert、delete、update开头的正则 ***/
    public static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 当前是否开启事务
        boolean synchronizationActive =
                TransactionSynchronizationManager.isActualTransactionActive();

        Object[] args = invocation.getArgs();
        MappedStatement statement = (MappedStatement) args[0];
        String lookupKey;

        // 非事务
        if (!synchronizationActive) {
            lookupKey = DynamicDataSourceHolder.DB_MASTER;

            // 读方法
            if (statement.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                // selectKey为自增id查询主键 SELECT LAST_INSERT_ID( )方法，使用主库
                if (statement.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                    lookupKey = DynamicDataSourceHolder.DB_MASTER;
                } else {
                    BoundSql boundSql = statement.getSqlSource().getBoundSql(args[1]);
                    // 获取sql
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
                    // 以insert、delete、update开头（主库）
                    if (sql.matches(REGEX)) {
                        lookupKey = DynamicDataSourceHolder.DB_MASTER;
                    } else {
                        lookupKey = DynamicDataSourceHolder.DB_SLAVE;
                    }
                }
            }
        } else {
            lookupKey = DynamicDataSourceHolder.DB_MASTER;
        }
        logger.debug("设置方法[{}] use[{}] Strategy, SqlCommonType [{}]",
                statement.getId(), lookupKey, statement.getSqlCommandType().name());
        // 设置数据库类型
        DynamicDataSourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 如果传入的对象是可执行的，将会被拦截
        if (target instanceof Executable) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }

}
