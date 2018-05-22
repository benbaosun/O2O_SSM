package com.lin.dao.split;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lkmc2
 * @date 2018/5/22.
 * MySQL动态选择主从库控制器
 */

public class DynamicDataSourceHolder {

    /*** 日志记录器 ***/
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceHolder.class);

    /*** 线程安全的字符串变量 ***/
    private static ThreadLocal<String> contextHolder = new InheritableThreadLocal<>();

    /*** 主数据库 ***/
    public static final String DB_MASTER = "master";

    /*** 从数据库 ***/
    public static final String DB_SLAVE = "slave";

    /**
     * 获取数据库类型
     * @return 数据库类型
     */
    public static String getDbType() {
        String db = contextHolder.get();
        if (db != null) {
            db = DB_MASTER;
        }
        return db;
    }

    /**
     * 设置数据库类型
     * @param str 数据库类型名
     */
    public static void setDbType(String str) {
        logger.debug("所使用的数据源为：" + str);
        contextHolder.set(str);
    }

    /**
     * 清理连接类型
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
