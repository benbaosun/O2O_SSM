package com.lin.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺管理员控制器
 */

@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {

    /**
     * 进行店铺操作路径映射
     * @return 映射后的网址
     */
    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }

    /**
     * 进行店铺列表显示路径映射
     * @return 映射后的网址
     */
    @RequestMapping(value = "/shoplist")
    public String shopList() {
        return "shop/shoplist";
    }

    /**
     * 进行店铺管理路径映射
     * @return 映射后的网址
     */
    @RequestMapping(value = "/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanage";
    }

    /**
     * 进行店铺管理路径映射
     * @return 映射后的网址
     */
    @RequestMapping(value = "/productcategorymanage")
    public String productCategoryManage() {
        return "shop/productcategorymanage";
    }

}
