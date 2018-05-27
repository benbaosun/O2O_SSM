package com.lin.service.impl;

import com.lin.dao.ProductCategoryDao;
import com.lin.entity.ProductCategory;
import com.lin.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lkmc2
 * @date 2018/5/27.
 * 产品分类服务实现类
 */

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> queryProductCategoryList(long shopId) {
        return productCategoryDao.queryProductCategoryList(shopId);
    }

}
