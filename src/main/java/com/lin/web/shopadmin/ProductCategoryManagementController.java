package com.lin.web.shopadmin;

import com.lin.dto.Result;
import com.lin.entity.*;
import com.lin.enums.ProductCategoryStateEnum;
import com.lin.service.ProductCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺管理控制器
 */

@Api(value = "店铺管理Controller", tags = {"店铺管理控制器"})
@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {

    @Autowired
    private ProductCategoryService productCategoryService;

    /**
     * 获取产品分类信息
     * @param request 请求
     * @return 产品分类信息
     */
    @ApiOperation(value = "获取产品分类列表", tags = "获取产品分类列表")
    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    private Result<List<ProductCategory>> getProductCategoryList(
            @ApiParam(name = "request", value = "请求", required = true) HttpServletRequest request) {
//        Shop shop = new Shop();
//        shop.setShopId(1L);
//        // 将店铺信息存入session
//        request.getSession().setAttribute("currentShop", shop);

        // 从session中获取店铺信息
        Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
        // 产品分类列表
        List<ProductCategory> list;

        // 店铺对象和店铺id非空（有权限操作）
        if (currentShop != null && currentShop.getShopId() > 0) {
            // 通过店铺id查询产品类别列表
            list = productCategoryService.getProductCategoryList(currentShop.getShopId());
            return new Result<>(true, list);
        }
        // 操作失败（枚举值）
        ProductCategoryStateEnum ps = ProductCategoryStateEnum.INNER_ERROR;
        return new Result<>(false, ps.getState(), ps.getStateInfo());
    }

}
