package com.lin.service.impl;


import com.lin.dao.ShopDao;
import com.lin.dto.ShopExecution;
import com.lin.entity.Shop;
import com.lin.enums.ShopStateEnum;
import com.lin.exceptions.ShopOperationException;
import com.lin.service.ShopService;
import com.lin.utils.FileUtil;
import com.lin.utils.ImageUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
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
                // 判断是否需要处理图片
                if (shopImgInputStream != null && StringUtils.isNotEmpty(fileName)) {
                    File imgFile = new File(fileName);
                    // 将图片流转换成文件
                    inputStreamToFile(shopImgInputStream, imgFile);
                    // 添加店铺图片
                    addShopImg(shop, imgFile);
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

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                // 1.判断是否需要处理图片
                if (shopImgInputStream != null && StringUtils.isNotEmpty(fileName)) {
                    // 根据id获取店铺
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopId() != null) {
                        // 删除店铺原有的图片
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    File imgFile = new File(fileName);
                    // 将图片流转换成文件
                    inputStreamToFile(shopImgInputStream, imgFile);
                    // 添加店铺图片
                    addShopImg(shop, imgFile);
                }
                // 2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            } catch (Exception e) {
                throw new ShopOperationException("modifyShop error:" + e.getMessage());
            }
        }
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
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

    /**
     * 将输入流装换成文件
     * @param ins 输入流
     * @param file 文件
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, buffer.length)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
