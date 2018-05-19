package com.lin.dao;

import com.lin.BaseTest;
import com.lin.entity.Area;
import com.lin.entity.PersonInfo;
import com.lin.entity.Shop;
import com.lin.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺查询测试
 */

public class ShopDaoTest extends BaseTest {

    @Autowired
    private ShopDao shopDao;

    /**
     * 测试插入店铺对象
     */
    @Test
    public void testInsertShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(1L);
        area.setAreaId(2);
        shopCategory.setShopCategoryId(1L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺");
        shop.setShopDesc("test");
        shop.setShopAddr("高德区");
        shop.setPhone("188206566");
        shop.setShopImg("test");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(1);
        shop.setAdvice("审核中");
        // 执行插入店铺对象
        int effectedNum = shopDao.insertShop(shop);
        assertEquals(1, effectedNum);
    }

    /**
     * 测试插入店铺对象
     */
    @Test
    public void testUpdateShop() {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopDesc("新的测试描述");
        shop.setShopAddr("新的测试地址");
        shop.setLastEditTime(new Date());
        // 执行更新店铺对象
        int effectedNum = shopDao.updateShop(shop);
        assertEquals(1, effectedNum);
    }
}
