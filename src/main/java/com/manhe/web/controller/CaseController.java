package com.manhe.web.controller;

import com.manhe.dal.dataobject.CaseCategoryDO;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CaseController {
    @RequestMapping("/case")
    public ModelAndView index(Integer categoryId) {
        ModelAndView mav = new ModelAndView("cases/case");
        //导航栏产品列表
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        //左侧案例分类列表
        List<CaseCategoryDO> caseCategory = caseCategoryService.find(null);
        //全部案例
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("id", categoryId);
        List<CaseCategoryDO> caseCategories = caseCategoryService.find(param);
        for (CaseCategoryDO caseCategoryDO : caseCategories) {
            Map<String, Object> caseParam = new HashMap<>();
            caseParam.put("categoryId", caseCategoryDO.getId());
            List<CaseDO> cases = caseService.find(caseParam);
            Map<String, Object> item = new HashMap<>();
            item.put("name", caseCategoryDO.getName());
            item.put("details", caseCategoryDO.getDetails());
            item.put("cases", cases);
            list.add(item);
        }
        mav.addObject("productCategory", productCategory);
        mav.addObject("caseCategory", caseCategory);
        mav.addObject("list", list);
        return mav;
    }
    @RequestMapping("/caseShow")
    public ModelAndView caseShow(Integer caseId) {
        ModelAndView mav = new ModelAndView("cases/caseshow");
        //导航栏产品列表
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        //左侧案例分类列表
        List<CaseCategoryDO> caseCategory = caseCategoryService.find(null);
        //案例
        Map<String, Object> param = new HashMap<>();
        param.put("id", caseId);
        CaseDO caseDO = caseService.get(param);
        mav.addObject("productCategory", productCategory);
        mav.addObject("caseCategory", caseCategory);
        mav.addObject("case", caseDO);
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
