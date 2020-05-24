package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.ContactDO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.ContactService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import com.manhe.service.ProductService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/list2")
    public Map<String, Object> list(@Param("name") String name,@Param("limits") Integer limits, @Param("page") Integer page, @Ignore Response response) {
        PageInfo pageInfo = PageInfo.genPageInfoPage(page == null ? 1 : page, limits == null ? 10 : limits);
        Map<String, Object> param = new HashMap<>();
        param.put("nickName", name);
        List<ContactDO> contactDOS = contactService.find(param, pageInfo);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", pageInfo.getTotalCount());
        resultMap.put("data", contactDOS);

        return resultMap;
    }

    @PostMapping("/contactSubmit")
    public String contactSubmit() {

        return "contact/contact";
    }

    @Autowired
    private ContactService contactService;
}
