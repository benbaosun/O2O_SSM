package com.lin.dao;

import com.lin.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺查询
 */

public interface ShopDao {

    /**
     * 分页查询店铺
     * 可输入的条件有：店铺名（模糊），店铺状态，店铺类别，区域id，owner
     * @param shopCondition 店铺查询条件
     * @param rowIndex 行号（从第几行取数据）
     * @param pageSize 返回数据的条数
     * @return 符合查询条件的店铺
     */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition,
                             @Param("rowIndex") int rowIndex,
                             @Param("pageSize")int pageSize);

    /**
     * 获取符合查询条件的店铺数量
     * @param shopCondition 店铺查询条件
     * @return 查询条件的店铺数量
     */
    int queryShopCount(@Param("shopCondition") Shop shopCondition);

    /**
     * 根据店铺id获取店铺信息
     * @param shopId 店铺id
     * @return 店铺对象
     */
    Shop queryByShopId(long shopId);

    /**
     * 新增店铺
     * @param shop 店铺对象
     * @return 受影响行数
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺信息
     * @param shop 店铺对象
     * @return 受影响行数
     */
    int updateShop(Shop shop);

}
