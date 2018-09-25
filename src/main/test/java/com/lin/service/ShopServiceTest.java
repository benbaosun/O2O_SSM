package com.lin.service;

import com.lin.BaseTest;
import com.lin.dto.ShopExecution;
import com.lin.entity.Area;
import com.lin.entity.PersonInfo;
import com.lin.entity.Shop;
import com.lin.entity.ShopCategory;
import com.lin.enums.ShopStateEnum;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺服务测试类
 */

public class ShopServiceTest extends BaseTest {

    @Autowired
    private ShopService shopService;

    @Test
    public void testGetShopList() {
        Shop shopCondition = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(2L);
        shopCondition.setShopCategory(shopCategory);
        ShopExecution se = shopService.getShopList(shopCondition, 2, 2);
        System.out.println("店铺列表数：" + se.getShopList().size());
        System.out.println("店铺总数：" + se.getCount());
    }

    @Test
    public void testModifyShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(1L);
        shop.setShopName("修改后的店铺名称");
        File shopImg = new File("G:\\android素材\\spygame素材\\layout背景\\gosick1.jpg");
        InputStream is = new FileInputStream(shopImg);
        ShopExecution shopExecution = shopService.modifyShop(shop, is, "ever.jpg");
        System.out.println("新的图片地址:" + shopExecution.getShop().getShopImg());
    }

    @Test
    public void testAddShop() {
        Shop shop = new Shop();
        PersonInfo owner = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        owner.setUserId(8L);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(10L);
        shop.setOwner(owner);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("测试的店铺3");
        shop.setShopDesc("test3");
        shop.setShopAddr("高德区3");
        shop.setPhone("1882065889");
        shop.setCreateTime(new Date());
        shop.setEnableStatus(ShopStateEnum.CHECK.getState());
        shop.setAdvice("审核中");

        File shopImg = new File("F:\\android素材\\波浪图.jpg");
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(shopImg);
            ShopExecution shopExecution = shopService.addShop(shop, fileInputStream, shopImg.getName());

            assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
