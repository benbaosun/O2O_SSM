package com.lin.service.impl;

import com.lin.dao.AreaDao;
import com.lin.entity.Area;
import com.lin.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/4/25.
 * 区域服务实现类
 */

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }
}
