package com.lin.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.dto.ShopExecution;
import com.lin.entity.PersonInfo;
import com.lin.entity.Shop;
import com.lin.enums.ShopStateEnum;
import com.lin.service.ShopService;
import com.lin.utils.HttpServletRequestUtil;
import com.lin.utils.ImageUtil;
import com.lin.utils.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺管理控制器
 */

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

    @Autowired
    private ShopService shopService;

    /**
     * 注册店铺
     * @param request 用户请求
     * @return 响应结果
     */
    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();
        // 1.接收并转换响应的参数，包括店铺以及图片信息
        // 获取请求中的参数
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;

        try {
            // 将json字符串转换成店铺对象
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            modelMap.put("success", false);
            modelMap.put("errMsg", e.getMessage());
            return modelMap;
        }
        // 店铺图片文件流
        CommonsMultipartFile shopImg = null;
        // 文件流解析器
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());

        // 请求中包含文件
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            // 获取页面上传的图片
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "上传图片不能为空");
            return modelMap;
        }

        // 2.注册店铺
        // 店铺对象和店铺图片非空
        if (shop != null && shopImg != null) {
            PersonInfo owner = new PersonInfo();
            owner.setUserId(1L);
            shop.setOwner(owner);
            // 店铺图片目标文件地址
            File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
            try {
                // 创建新文件
                shopImgFile.createNewFile();
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }

            try {
                // 将输入流转换成文件
                inputStreamToFile(shopImg.getInputStream(), shopImgFile);
            } catch (IOException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.getMessage());
                return modelMap;
            }

            // 注册店铺到数据库
            ShopExecution shopExecution = shopService.addShop(shop, shopImgFile);

            // 店铺传输对象的状态为审核中（成功）
            if (shopExecution.getState() == ShopStateEnum.CHECK.getState()) {
                modelMap.put("success", true);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", shopExecution.getStateInfo());
            }

            // 3.返回结果
            return modelMap;
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入店铺信息");
            return modelMap;
        }
    }

    /**
     * 将输入流转换成文件
     * @param ins 输入流
     * @param file 目标文件对象
     */
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            // 复制输入流到目标文件
            Files.copy(ins, file.toPath());
        } catch (IOException e) {
            throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
        }
//        try(FileOutputStream os = new FileOutputStream(file)) {
//
//            int byteRead = 0;
//            byte[] buffer = new byte[1024];
//            while ((byteRead = ins.read(buffer)) != -1) {
//                os.write(buffer, 0, byteRead);
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("调用inputStreamToFile产生异常：" + e.getMessage());
//        } finally {
//            if (ins != null) {
//                try {
//                    ins.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}