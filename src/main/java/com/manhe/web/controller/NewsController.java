package com.manhe.web.controller;

import com.manhe.dal.dataobject.NewsDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.pageUtils.PageInfo;
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
public class NewsController {
    @RequestMapping("/news")
    public ModelAndView news(Integer pageNo) {
        pageNo = pageNo == null || pageNo == 0 ? 1 : pageNo;
        ModelAndView mav = new ModelAndView("news/news");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);

        PageInfo pageInfo = PageInfo.genPageInfoPage(pageNo, 20);
        //List<NewsDO> news = newsService.page(null, pageInfo);
        List<NewsDO> news = newsService.find(null);

        mav.addObject("productCategory", productCategory);
        mav.addObject("news", news);
        mav.addObject("page", pageInfo);
        return mav;
    }

    @RequestMapping("/newsShow")
    public ModelAndView newsShow(Integer newsId) {

        ModelAndView mav = new ModelAndView("news/newsshow");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        HashMap<String,Object> param = new HashMap<>();
        param.put("id",newsId);
        NewsDO newsDO = newsService.get(param);

        mav.addObject("productCategory", productCategory);
        mav.addObject("news", newsDO);
        return mav;
    }
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
