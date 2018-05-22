package com.lin.dao.split;


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author lkmc2
 * @date 2018/5/22.
 * MySQL动态选择主从库类
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDbType();
    }

}
