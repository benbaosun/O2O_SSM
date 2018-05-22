package com.lin.service.impl;


import com.lin.dao.ShopDao;
import com.lin.dto.ShopExecution;
import com.lin.entity.Shop;
import com.lin.enums.ShopStateEnum;
import com.lin.exceptions.ShopOperationException;
import com.lin.service.ShopService;
import com.lin.utils.FileUtil;
import com.lin.utils.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.util.Date;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺服务实现类
 */

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public ShopExecution addShop(Shop shop, File shopImg) {
        // 空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            // 设置店铺可用状态
            shop.setEnableStatus(0);
            // 设置店铺创建时间
            shop.setCreateTime(new Date());
            // 设置店铺上次编辑时间
            shop.setLastEditTime(new Date());
            // 插入店铺到数据库获取受影响行数
            int effectedNum = shopDao.insertShop(shop);

            // 受影响行数小于等于0
            if (effectedNum <= 0) {
                throw new ShopOperationException("店铺创建失败");
            } else {
                // 商品图片非空
                if (shopImg != null) {
                    // 存储图片
                    addShopImg(shop, shopImg);

                    // 更新店铺的地址图片
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }

        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        // 返回店铺传输类，状态：审核中，带店铺信息
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    /**
     * 添加店铺图片
     * @param shop 店铺对象
     * @param shopImg 店铺图片
     */
    private void addShopImg(Shop shop, File shopImg) {
        // 获取shop图片目录的相对值路径
        String dest = FileUtil.getShopImagePath(shop.getShopId());
        // 店铺图片地址
        String shopImgAddr = ImageUtil.generateThumbnail(shopImg, dest);
        // 设置店铺图片
        shop.setShopImg(shopImgAddr);
    }

}
