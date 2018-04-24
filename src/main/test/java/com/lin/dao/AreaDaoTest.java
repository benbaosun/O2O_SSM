package com.lin.dao;

import com.lin.BaseTest;
import com.lin.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @author lkmc2
 * @date 2018/4/25.
 * 区域查询测试
 */

public class AreaDaoTest extends BaseTest {

    @Autowired
    private AreaDao areaDao;

    @Test
    public void testQueryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2, areaList.size());
    }

}
