package com.lin.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lin.entity.Shop;
import com.lin.utils.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        // 2.注册店铺
        // 3.返回结果
        return modelMap;
    }

}
