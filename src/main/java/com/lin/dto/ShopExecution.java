package com.lin.dto;

import com.lin.entity.Shop;
import com.lin.enums.ShopStateEnum;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 商店传输对象
 */

public class ShopExecution {
    /*** 结果状态 ***/
    private int state;

    /*** 状态标志 ***/
    private String stateInfo;

    /*** 店铺数量 ***/
    private int count;

    /*** 操作的店铺对象（增删改查的时候用到） ***/
    private Shop shop;

    /*** 店铺列表（查询店铺列表的时候用到） ***/
    private List<Shop> shopList;

    public ShopExecution() {
    }

    /**
     * 店铺操作失败时候的构造器
     * @param stateEnum 商店状态枚举
     */
    public ShopExecution(ShopStateEnum stateEnum) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    /**
     * 店铺操作成功时候的构造器
     * @param stateEnum 商店状态枚举
     */
    public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }

    /**
     * 店铺操作成功时候的构造器
     * @param stateEnum 商店状态枚举
     */
    public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shopList = shopList;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
