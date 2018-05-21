package com.lin.service.impl;

import com.lin.dao.ShopCategoryDao;
import com.lin.entity.ShopCategory;
import com.lin.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/21.
 * 店铺分类服务实现类
 */

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Override
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {
        return shopCategoryDao.queryShopCategory(shopCategoryCondition);
    }

}
