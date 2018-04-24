package com.lin.dao;

import com.lin.entity.Area;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/4/25.
 * 区域查询
 */

public interface AreaDao {
    /**
     * 查询区域列表
     * @return 区域列表
     */
    List<Area> queryArea();
}
