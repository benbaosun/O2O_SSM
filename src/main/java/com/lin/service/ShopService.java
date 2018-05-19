package com.lin.service;

import com.lin.dto.ShopExecution;
import com.lin.entity.Shop;

import java.io.File;

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

}
