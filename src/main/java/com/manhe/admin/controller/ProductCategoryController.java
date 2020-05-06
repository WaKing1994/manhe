package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminProductCategoryController")
@RequestMapping("/admin/productCategory")
public class ProductCategoryController {
    @RequestMapping("/list")
    public ModelAndView product() {

        List<ProductCategoryDO> productCategoryDOS = productCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/productCategory/list");
        mav.addObject("productCategorys", productCategoryDOS);
        return mav;
    }

    @PostMapping("/list2")
    public Map<String,Object> list(@Param("limits") Integer limits, @Param("page") Integer page, @Ignore Response response) {
        PageInfo pageInfo = PageInfo.genPageInfoPage(page == null ? 1 : page, limits == null ? 10 : limits);
        List<ProductCategoryDO> productCategoryDOS = productCategoryService.find(null, pageInfo);
        Map<String,Object> resultMap = new HashMap<String, Object>();

        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",pageInfo.getTotalCount());
        resultMap.put("data",productCategoryDOS);

        return resultMap;
    }
    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("admin/productCategory/add");
        return mav;
    }

    @PostMapping("/addSubmit")
    public Response addSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        String name = req.get("name").toString();
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        productCategoryDO.setName(name);
        productCategoryDO.setDetails(details);
        productCategoryDO.setPriority(priority);
        productCategoryService.insert(productCategoryDO);
        return response;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        ProductCategoryDO productCategoryDO = productCategoryService.get(param);
        ModelAndView mav = new ModelAndView("admin/productCategory/edit");
        mav.addObject("productCategory", productCategoryDO);
        return mav;
    }

    @PostMapping("/editSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        Long id = Long.valueOf(req.get("id").toString());
        String name = req.get("name").toString();
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        ProductCategoryDO productCategoryDO = new ProductCategoryDO();
        productCategoryDO.setId(id);
        productCategoryDO.setName(name);
        productCategoryDO.setDetails(details);
        productCategoryDO.setPriority(priority);
        productCategoryService.update(productCategoryDO);
        return response;
    }

    @PostMapping("/delete")
    public Response delete(Long id, @Ignore Response response) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        productCategoryService.delete(param);
        return response;
    }


    @Autowired
    private ProductCategoryService productCategoryService;

}
