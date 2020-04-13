package com.manhe.web.controller;

import com.manhe.common.StringUtils;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.CaseService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {
    @RequestMapping({"/", "/home/index"})
    public ModelAndView index(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("h5/index");
        String ua = request.getHeader("User-Agent");
        if (!StringUtils.checkAgentIsMobile(ua)) {
            mav = new ModelAndView("home/index");
        }

        List<CaseDO> cases = caseService.find(null);
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        List<NewsDO> news = newsService.find(null);

        mav.addObject("cases", cases);
        mav.addObject("productCategory", productCategory);
        mav.addObject("news", news);
        return mav;
    }

    @Autowired
    private CaseService caseService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
