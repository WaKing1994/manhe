package com.manhe.web.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.*;
import com.manhe.dal.dto.ProductDTO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.*;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @RequestMapping("/product")
    public ModelAndView product(Integer categoryId) {

        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        PageInfo pageInfo = PageInfo.genPageInfoPage(1, 6);
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("id", categoryId);
        List<ProductCategoryDO> productCategoryDOS = productCategoryService.find(param);
        for (ProductCategoryDO productCategoryDO : productCategoryDOS) {
            Map<String, Object> productParam = new HashMap<>();
            productParam.put("categoryId", productCategoryDO.getId());
            List<ProductDTO> products = productService.find(productParam, pageInfo);
            Map<String, Object> item = new HashMap<>();
            item.put("name", productCategoryDO.getName());
            item.put("details", productCategoryDO.getDetails());
            item.put("id", productCategoryDO.getId());
            item.put("products", products);
            list.add(item);
        }
        ModelAndView mav = new ModelAndView("product/product");
        mav.addObject("productCategory", productCategory);
        mav.addObject("list", list);
        return mav;
    }

    @PostMapping("/product/like")
    public Map<String, Object> like(@RequestBody Map<String, Object> param, @Ignore Response response) {
        ProductDO productDO = productService.get(param);
        productDO.setLikes(productDO.getLikes() + 1);
        productService.update(productDO);

        Map<String, Object> configParam = new HashMap<>();
        configParam.put("name", "产品点赞数");
        ConfigDO configDO = configService.get(configParam);
        if (configDO != null) {
            Integer count = Integer.valueOf(configDO.getValue()) + 1;
            configDO.setValue(count.toString());
            configService.update(configDO);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg", "success");

        return resultMap;
    }

    @PostMapping("/product/page")
    public Map<String, Object> page(@RequestBody Map<String, Object> param, @Ignore Response response) {

        Integer categoryId = Integer.valueOf(param.get("categoryId").toString());
        Integer pageNo = Integer.valueOf(param.get("pageNo").toString());

        Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("categoryId", categoryId);
        PageInfo pageInfo = PageInfo.genPageInfoPage(pageNo, 6);
        List<ProductDTO> productDTOS = productService.find(pageParam,pageInfo);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("code", 0);
        resultMap.put("msg", "success");
        resultMap.put("data", productDTOS);
        return resultMap;
    }

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private ConfigService configService;
}
