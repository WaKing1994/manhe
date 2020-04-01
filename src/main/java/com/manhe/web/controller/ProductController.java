package com.manhe.web.controller;

import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.service.CaseService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {
    @RequestMapping("/product")
    public ModelAndView product(Integer categoryId) {

        List<ProductCategoryDO> productCategory = productCategoryService.find(null);

        Map<String,Object> productParam = new HashMap<>();
        productParam.put("categoryId",categoryId);
        List<ProductDO> products = productService.find(productParam);
        ModelAndView mav = new ModelAndView("product/product");
        mav.addObject("productCategory", productCategory);
        mav.addObject("products", products);
        return mav;
    }

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
