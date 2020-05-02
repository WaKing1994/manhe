package com.manhe.admin.controller;

import com.manhe.dal.dataobject.ContactDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.ContactService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController("adminContactController")
@RequestMapping("/admin/contact")
public class ContactController {
    @RequestMapping("/list")
    public ModelAndView list() {

        ModelAndView mav = new ModelAndView("admin/contact/list");
        List<ContactDO> contactDOS = contactService.find(null);
        mav.addObject("contacts", contactDOS);

        return mav;
    }
    @PostMapping("/contactSubmit")
    public String contactSubmit() {

        return "contact/contact";
    }

    @Autowired
    private ContactService contactService;
}
