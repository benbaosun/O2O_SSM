package com.lin.service;

import com.lin.entity.ProductCategory;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/27.
 * 产品分类服务
 */

public interface ProductCategoryService {

    /**
     * 通过店铺id查询店铺商品类别
     * @param shopId 店铺id
     * @return 店铺商品类别列表
     */
    List<ProductCategory> queryProductCategoryList(long shopId);

}
