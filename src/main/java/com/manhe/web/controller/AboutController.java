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
public class AboutController {
    @RequestMapping("/about")
    public ModelAndView about(String type) {
        ModelAndView mav = new ModelAndView("about/about");

        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);
        return mav;
    }
    @RequestMapping("/about1")
    public ModelAndView about1(String type) {
        ModelAndView mav = new ModelAndView("about/about1");

        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);
        return mav;
    }
    @RequestMapping("/about2")
    public ModelAndView about2(String type) {
        ModelAndView mav = new ModelAndView("about/about2");

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
