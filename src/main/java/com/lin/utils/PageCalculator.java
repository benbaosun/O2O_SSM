package com.lin.utils;

/**
 * @author lkmc2
 * @date 2018/5/27.
 * 页数转换工具类
 */

public class PageCalculator {

    /**
     * 计算获取行数的开始位置
     * 默认从第0行开始获取数据，如果当前是第2页，页面数据条数为5条，
     * 则开始下标为(2 - 1) * 5 = 5（数据库返回结果的开始位置）
     * @param pageIndex 页数
     * @param pageSize 条数
     * @return 获取行数的开始位置
     */
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }

}
