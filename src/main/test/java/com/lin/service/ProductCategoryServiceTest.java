package com.lin.service;

import com.lin.BaseTest;
import com.lin.dao.ProductCategoryDao;
import com.lin.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/27.
 * 产品分类查询测试
 */

public class ProductCategoryServiceTest extends BaseTest {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 根据
     */
    @Test
    public void testQueryProductCategoryList() {
        long shopId = 1;
        List<ProductCategory> productCategories = productCategoryService.queryProductCategoryList(shopId);
        System.out.println("该店铺自定义类别数为：" + productCategories.size());
    }

}
