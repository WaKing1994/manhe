package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminProductController")
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
    public Response addSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        return response;
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

    @PostMapping("/editSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        return response;
    }

    @PostMapping("/delete")
    public Response delete(Long id, @Ignore Response response) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        productService.delete(param);
        return response;
    }

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
