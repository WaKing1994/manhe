package com.manhe.service;

import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.pageUtils.PageInfo;

import java.util.List;
import java.util.Map;

public interface ProductCategoryService {
    public void insert(ProductCategoryDO productCategoryDO);

    public void update(ProductCategoryDO productCategoryDO);

    public ProductCategoryDO get(Map<String, Object> params);

    public void delete(Map<String, Object> params);

    public List<ProductCategoryDO> find(Map req);

    public List<ProductCategoryDO> find(Map req, PageInfo pageInfo);

}
