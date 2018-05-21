package com.lin.service;

import com.lin.entity.ShopCategory;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/21.
 * 店铺分类服务
 */

public interface ShopCategoryService {

    /**
     * 获取符合条件的店铺分类列表
     * @param shopCategoryCondition 店铺分类条件
     * @return 符合条件的店铺分类列表
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);

}
