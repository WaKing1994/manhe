package com.manhe.web.controller;

import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ServiceController {
    @RequestMapping("/service")
    public ModelAndView service() {

        ModelAndView mav = new ModelAndView("service/service");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);

        return mav;
    }
    @RequestMapping("/service1")
    public ModelAndView service1() {

        ModelAndView mav = new ModelAndView("service/service1");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);

        return mav;
    }
    @RequestMapping("/service2")
    public ModelAndView service2() {

        ModelAndView mav = new ModelAndView("service/service2");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);

        return mav;
    }
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
