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
     * 添加店铺
     * @param shop 店铺对象
     * @param shopImg 店铺图片
     * @return 店铺传输对象
     */
    ShopExecution addShop(Shop shop, File shopImg);


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
