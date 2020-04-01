package com.manhe.web.controller;

import com.manhe.dal.dataobject.CaseCategoryDO;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CaseController {
    @RequestMapping("/case")
    public ModelAndView index(Integer categoryId) {
        ModelAndView mav = new ModelAndView("cases/case");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);

        List<CaseCategoryDO> caseCategoryDOS = caseCategoryService.find(null);
        Map<String,Object> caseParam = new HashMap<>();
        caseParam.put("categoryId",categoryId);
        List<CaseDO> cases = caseService.find(caseParam);
        mav.addObject("caseCategoryDOS", caseCategoryDOS);
        mav.addObject("cases", cases);
        return mav;
    }

    @Autowired
    private ProductService productService;
    @Autowired
    private CaseCategoryService caseCategoryService;
    @Autowired
    private CaseService caseService;
    @Autowired
    private ProductCategoryService productCategoryService;
}
