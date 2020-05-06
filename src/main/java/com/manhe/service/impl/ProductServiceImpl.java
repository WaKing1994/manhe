package com.manhe.service.impl;

import com.manhe.dal.dao.ProductDAO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDAO dao;


    @Override
    public void insert(ProductDO productDO) {
        dao.insert(productDO);
    }

    @Override
    public void update(ProductDO productDO) {
        dao.update(productDO);
    }

    @Override
    public ProductDO get(Map<String, Object> params) {
        return dao.get(params);
    }

    @Override
    public void delete(Map<String, Object> params) {
        dao.delete(params);
    }

    @Override
    public List<ProductDO> find(Map req) {
        return dao.getListByMap(req);
    }

    @Override
    public List<ProductDO> find(Map req, PageInfo pageInfo) {
        return dao.getListByMap(req, pageInfo);
    }
}
