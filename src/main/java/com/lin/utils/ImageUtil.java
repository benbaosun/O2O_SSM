package com.lin.utils;


import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 图片工具类
 */

public class ImageUtil {

    /*** 图片根路径 ***/
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();

    /*** 日期格式 ***/
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");

    /*** 随机对象 ***/
    private static final Random RANDOM = new Random();

    /**
     * 生成缩略图
     * @param thumbnail 用户上传的图片
     * @param targetAddr 上传目标地址
     * @return 生成后的缩略图地址
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        // 生成随机文件名
        String realFileName = getRandomFileName();
        // 获取文件后缀名
        String extension = getFileExtension(thumbnail);
        // 创建目标目录
        makeDirPath(targetAddr);
        // 组合相对路径
        String relativeAddr = targetAddr + realFileName + extension;
        // 生成目标图片文件
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);

        try {
            // 生成带水印的图片
            Thumbnails.of(thumbnail.getInputStream())
                    .size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建文件夹
     * @param targetAddr 目标文件夹路径
     */
    private static void makeDirPath(String targetAddr) {
        // 文件夹父路径
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        // 如果该文件夹不存在
        if (!dirPath.exists()) {
            // 创建文件夹
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的拓展名
     * @param file 用户上传的文件流
     * @return 文件拓展名
     */
    private static String getFileExtension(CommonsMultipartFile file) {
        // 获取文件名
        String filename = file.getOriginalFilename();
        // 截取拓展名
        return filename.substring(filename.indexOf("."));
    }

    /**
     * 生成随机文件名，当前年月日小时分钟秒钟+五位随机数
     * @return 随机文件名
     */
    private static String getRandomFileName() {
        // 获取随机的五位数
        int randomNum = RANDOM.nextInt(89999) + 10000;
        // 获取现在时间格式化后的字符串
        String nowTimeStr;
        // 加锁解决线程安全问题
        synchronized (DATE_FORMAT) {
            nowTimeStr = DATE_FORMAT.format(new Date());
        }
        return randomNum + nowTimeStr;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(basePath);

        // 生成带水印的图片
        Thumbnails.of(new File("G:\\android素材\\波浪图.jpg"))
                .size(200, 200)
                .watermark(Positions.BOTTOM_RIGHT,
                        ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
                .outputQuality(0.8f)
                .toFile("G:\\android素材\\波浪图33.jpg");

    }
}
