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
public class ContactController {
    @RequestMapping("/contact")
    public ModelAndView service() {

        ModelAndView mav = new ModelAndView("contact/contact");
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
