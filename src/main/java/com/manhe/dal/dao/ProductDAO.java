package com.manhe.dal.dao;

import com.manhe.dal.dataobject.ProductDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductDAO {

    void insert(ProductDO productDO);

    void update(ProductDO productDO);

    void delete(Map<String, Object> params);

    ProductDO get(Map<String, Object> params);

    List<ProductDO> getListByMap(Map<String, Object> req);

}
