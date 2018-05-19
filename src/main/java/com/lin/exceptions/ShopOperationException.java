package com.lin.exceptions;

/**
 * @author lkmc2
 * @date 2018/5/19.
 * 店铺操作异常
 */

public class ShopOperationException extends RuntimeException {

    public ShopOperationException(String message) {
        super(message);
    }

}
