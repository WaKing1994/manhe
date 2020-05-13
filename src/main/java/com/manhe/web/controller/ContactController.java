package com.manhe.web.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.ContactDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.ContactService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ContactController {
    @RequestMapping("/contact")
    public ModelAndView service() {

        ModelAndView mav = new ModelAndView("contact/contact");
        List<ProductCategoryDO> productCategory = productCategoryService.find(null);
        mav.addObject("productCategory", productCategory);

        return mav;
    }

    @PostMapping("/contactSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {

        String name = param.get("name").toString();
        String email = param.get("email").toString();
        String mobile = param.get("mobile").toString();
        String city = param.get("city").toString();
        String comment = param.get("comment").toString();
        ContactDO model = new ContactDO();
        model.setNickName(name);
        model.setEmail(email);
        model.setArea(city);
        model.setContext(comment);
        model.setMobile(mobile);
        contactService.insert(model);
        return response;
    }

    @Autowired
    private ContactService contactService;
    @Autowired
    private ProductCategoryService productCategoryService;

}
