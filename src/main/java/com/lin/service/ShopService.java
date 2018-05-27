package com.lin.service;

import com.lin.dto.ShopExecution;
import com.lin.entity.Shop;
import com.lin.exceptions.ShopOperationException;

import java.io.File;
import java.io.InputStream;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺服务类
 */

public interface ShopService {

    /**
     * 获取符合条件的店铺列表
     * @param shopCondition 店铺查询条件
     * @param pageIndex 开始查询位置（从第几行开始）
     * @param pageSize 返回查询结果条数
     * @return 店铺传输对象
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 添加店铺
     * @param shop 店铺
     * @param shopImgInputStream 图片流
     * @param fileName 图片名
     * @return 商店传输对象
     * @throws ShopOperationException 店铺操作异常
     */
    ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;


    /**
     * 修改店铺信息
     * @param shop 店铺
     * @param shopImgInputStream 图片流
     * @param fileName 图片名
     * @return 商店传输对象
     * @throws ShopOperationException 店铺操作异常
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    /**
     * 根据店铺id获取店铺对象
     * @param shopId 店铺id
     * @return 店铺对象
     */
    Shop getByShopId(long shopId);
}
