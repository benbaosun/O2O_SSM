package com.lin.utils;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 路径工具类
 */

public class PathUtil {

    /*** 分隔符 ***/
    private static String separator = System.getProperty("file.separator");

    /*** Windows操作系统 ***/
    public static final String WIN_SYSTEM = "win";

    /**
     * 获取图片根目录
     * @return 图片根目录
     */
    public static String getImgBasePath() {
        // 获取操作系统类型
        String os = System.getProperty("os.name");
        // 根目录路径
        String basePath;

        //操作系统为Windows
        if (os.toLowerCase().startsWith(WIN_SYSTEM)) {
            basePath = "D:/projectdev/image";
        } else {
            basePath = "/home/lin/image";
        }

        // 替换分隔符
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /**
     * 获取店铺图片路径
     * @param shopId 店铺id
     * @return 对应id的店铺图片路径
     */
    public static String getShopImagePath(long shopId) {
        // 图片路径
        String imagePath = "/upload/item/shop/" + shopId + "/";
        // 替换分隔符
        return imagePath.replace("/", separator);
    }
}
