package com.manhe.dal.dao;

import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.pageUtils.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductCategoryDAO {

    void insert(ProductCategoryDO productCategoryDO);

    void update(ProductCategoryDO productCategoryDO);

    void delete(Map<String, Object> params);

    ProductCategoryDO get(Map<String, Object> params);

    List<ProductCategoryDO> getListByMap(Map<String, Object> req);

    List<ProductCategoryDO> getListByMap(Map<String, Object> req, PageInfo pageInfo);
}
