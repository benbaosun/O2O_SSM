package com.lin.dao;

import com.lin.entity.ProductCategory;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/27.
 * 商品分类查询
 */

public interface ProductCategoryDao {

    /**
     * 通过店铺id查询产品类别列表
     * @param shopId 店铺id
     * @return 产品类别列表
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

}
