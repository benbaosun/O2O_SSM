package com.lin.utils;

/**
 * @author lkmc2
 * @date 2018/5/22.
 * 文件工具类
 */

public class FileUtil {

    /*** 分隔符 ***/
    private static String separator = System.getProperty("file.separator");

    /**
     * 获取店铺图片存储路径
     * @param shopId 店铺id
     * @return 店铺图片存储路径
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/images/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }

}
