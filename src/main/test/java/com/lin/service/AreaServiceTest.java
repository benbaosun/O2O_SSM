package com.lin.service;

import com.lin.BaseTest;
import com.lin.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author lkmc2
 * @date 2018/4/25.
 * 区域服务测试类
 */

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        assertEquals("广东", areaList.get(0).getAreaName());
    }

}
