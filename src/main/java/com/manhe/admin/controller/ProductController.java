package com.manhe.admin.controller;

import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller("adminProductController")
@RequestMapping("/admin/product")
public class ProductController {
    @RequestMapping("/list")
    public ModelAndView product(Integer categoryId) {
        Map<String, Object> param = new HashMap<>();
        param.put("categoryId", categoryId);
        List<ProductDO> productDOS = productService.find(param);
        ModelAndView mav = new ModelAndView("admin/product/list");
        mav.addObject("products", productDOS);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/product/add");
        mav.addObject("productCategory", productCategory);
        return mav;
    }

    @PostMapping("/addSubmit")
    public String addSubmit(@RequestBody Map<String,Object> param) {
        return "success";
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        ProductDO productDO = productService.get(param);
        ModelAndView mav = new ModelAndView("admin/product/edit");
        mav.addObject("product", productDO);
        return mav;
    }

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
