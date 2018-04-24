package com.lin.service;

import com.lin.entity.Area;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/4/25.
 * 区域服务
 */

public interface AreaService {
    /**
     * 获取区域列表
     * @return 区域列表
     */
    List<Area> getAreaList();
}
