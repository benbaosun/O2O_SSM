package com.lin.dao;

import com.lin.BaseTest;
import com.lin.entity.Area;
import com.lin.entity.PersonInfo;
import com.lin.entity.Shop;
import com.lin.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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
     * 测试查询符合查询条件的店铺数量
     */
    @Test
    public void testQueryShopCount() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        int count = shopDao.queryShopCount(shopCondition);
        System.out.println("符合查询条件的店铺数量：" + count);
    }

    /**
     * 测试查询符合查询条件的店铺列表
     */
    @Test
    public void testQueryShopList() {
        Shop shopCondition = new Shop();
        PersonInfo owner = new PersonInfo();
        owner.setUserId(1L);
        shopCondition.setOwner(owner);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
        System.out.println("店铺列表的大小：" + shopList.size());

        ShopCategory sc = new ShopCategory();
        sc.setShopCategoryId(2L);
        shopCondition.setShopCategory(sc);
        shopList = shopDao.queryShopList(shopCondition, 0, 4);
        System.out.println("店铺分类为2的店铺列表大小：" + shopList.size());
    }

    /**
     * 测试根据店铺id查询店铺
     */
    @Test
    public void testQueryByShopId() {
        long shopId = 1L;
        Shop shop = shopDao.queryByShopId(shopId);

        System.out.println("areaId:" + shop.getArea().getAreaId());
        System.out.println("areaName:" + shop.getArea().getAreaName());
    }

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
