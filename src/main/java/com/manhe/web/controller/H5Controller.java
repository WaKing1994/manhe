package com.manhe.web.controller;


import com.manhe.service.CaseService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class H5Controller {
    @RequestMapping("/h5")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("h5/index");
        return mav;
    }

    @Autowired
    private CaseService caseService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
