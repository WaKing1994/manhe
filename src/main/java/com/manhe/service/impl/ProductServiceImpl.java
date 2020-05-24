package com.manhe.service.impl;

import com.manhe.dal.dao.ProductDAO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.dto.ProductDTO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("productService")
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductDAO dao;
    @Autowired
    private ProductCategoryService productCategoryService;

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
    public List<ProductDTO> find(Map req, PageInfo pageInfo) {

        List<ProductDTO> list = new ArrayList<>();
        List<ProductDO> productDOS = dao.getListByMap(req, pageInfo);
        for (ProductDO productDO : productDOS) {
            ProductDTO productDTO = new ProductDTO();
            BeanUtils.copyProperties(productDO, productDTO);
            {
                Map<String, Object> param = new HashMap<>();
                param.put("id", productDO.getCategoryId());
                ProductCategoryDO productCategoryDO = productCategoryService.get(param);
                if (productCategoryDO != null) {
                    productDTO.setCategoryName(productCategoryDO.getName());
                }
            }
            list.add(productDTO);
        }
        return list;
    }
}
