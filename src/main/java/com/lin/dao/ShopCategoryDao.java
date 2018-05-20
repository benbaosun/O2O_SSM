package com.lin.dao;

import com.lin.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/20.
 * 店铺分类查询
 */

public interface ShopCategoryDao {

    /**
     * 获取符合条件的店铺分类列表
     * @param shopCategoryCondition 店铺分类查询条件
     * @return 符合条件的店铺分类列表
     */
    List<ShopCategory> queryShopCategory(@Param(("shopCategoryCondition"))
                                                 ShopCategory shopCategoryCondition);

}
