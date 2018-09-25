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
 * @date 2018/5/20.
 * 店铺分类查询测试
 */

public class ShopCategoryDaoTest extends BaseTest {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    /**
     * 测试查询符合条件的店铺分类
     */
    @Test
    public void testQueryShopCategory() {
        List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
        assertEquals(12, shopCategoryList.size());

        ShopCategory testCategory = new ShopCategory();
        ShopCategory parentCategory = new ShopCategory();
        parentCategory.setShopCategoryId(10L);
        testCategory.setParent(parentCategory);
        shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
        assertEquals(2, shopCategoryList.size());
        System.out.println(shopCategoryList.get(0).getShopCategoryName());
    }

}
