package com.manhe.service.impl;

import com.manhe.dal.dao.ProductCategoryDAO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("productCategoryService")
public class ProductCategoryServiceImpl implements ProductCategoryService {


    @Autowired
    private ProductCategoryDAO dao;


    @Override
    public void insert(ProductCategoryDO productCategoryDO) {
        dao.insert(productCategoryDO);
    }

    @Override
    public void update(ProductCategoryDO productCategoryDO) {
        dao.update(productCategoryDO);
    }

    @Override
    public ProductCategoryDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<ProductCategoryDO> find(Map req) {
        return dao.getListByMap(req);
    }
}
