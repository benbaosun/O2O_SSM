package com.lin.dao;

import com.lin.entity.Shop;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺查询
 */

public interface ShopDao {
    /**
     * 新增店铺
     * @param shop 店铺对象
     * @return 受影响行数
     */
    int insertShop(Shop shop);

}
