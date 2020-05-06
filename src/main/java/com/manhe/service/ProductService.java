package com.manhe.service;

import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.pageUtils.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProductService {
    public void insert(ProductDO productDO);

    public void update(ProductDO productDO);

    public ProductDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<ProductDO> find(Map req);

    public List<ProductDO> find(Map req, PageInfo pageInfo);

}
