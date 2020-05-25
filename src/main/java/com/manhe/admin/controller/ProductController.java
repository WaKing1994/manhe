package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.dto.ProductDTO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.Param;
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
    public ModelAndView product(Integer categoryId, Integer pageNo) {
        PageInfo pageInfo = PageInfo.genPageInfoPage(pageNo == null ? 1 : pageNo, 10);
        Map<String, Object> param = new HashMap<>();
        param.put("categoryId", categoryId);
        List<ProductDTO> productDTOS = productService.find(param, pageInfo);
        List<ProductCategoryDO> productCategorys = productCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/product/list");
        mav.addObject("productCategory", productCategorys);
        mav.addObject("products", productDTOS);
        mav.addObject("totalCounts", pageInfo.getTotalCount());
        return mav;
    }

    @PostMapping("/list2")
    public Map<String, Object> list(@Param("productName") String productName,@Param("categoryName") String categoryName, @Param("limits") Integer limits, @Param("page") Integer page, @Ignore Response response) {
        PageInfo pageInfo = PageInfo.genPageInfoPage(page == null ? 1 : page, limits == null ? 10 : limits);
        Map<String, Object> param = new HashMap<>();
        param.put("name", productName);
        param.put("categoryName", categoryName);
        List<ProductDTO> productDTOS = productService.find(param, pageInfo);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", pageInfo.getTotalCount());
        resultMap.put("data", productDTOS);

        return resultMap;
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
        Map<String, Object> req = (HashMap) param.get("field");
        String name = req.get("name").toString();
        Long categoryId = Long.valueOf(req.get("categoryId").toString());
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        String banner = req.get("banner").toString();
        ProductDO productDO = new ProductDO();
        productDO.setName(name);
        productDO.setCategoryId(categoryId);
        productDO.setDetails(details);
        productDO.setPriority(priority);
        productDO.setBanner(banner);
        productService.insert(productDO);
        return response;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        ProductDO productDO = productService.get(param);
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/product/edit");

        mav.addObject("productCategory", productCategory);
        mav.addObject("product", productDO);
        return mav;
    }

    @PostMapping("/editSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        Long id = Long.valueOf(req.get("id").toString());
        String name = req.get("name").toString();
        Long categoryId = Long.valueOf(req.get("categoryId").toString());
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        String banner = req.get("banner").toString();
        ProductDO productDO = new ProductDO();
        productDO.setId(id);
        productDO.setName(name);
        productDO.setCategoryId(categoryId);
        productDO.setDetails(details);
        productDO.setPriority(priority);
        productDO.setBanner(banner);
        productService.update(productDO);
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
