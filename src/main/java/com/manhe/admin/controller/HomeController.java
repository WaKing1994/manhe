package com.manhe.admin.controller;

import com.manhe.dal.dataobject.ConfigDO;
import com.manhe.service.CaseService;
import com.manhe.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Controller("adminHomeController")
@RequestMapping("/admin/home")
public class HomeController {
    @GetMapping("/index")
    public ModelAndView login() {

        Map<String,Object> param = new HashMap<>();
        param.put("name","首页浏览数");
        ConfigDO home = configService.get(param);
        param.put("name","工程浏览数");
        ConfigDO cases = configService.get(param);
        param.put("name","产品点赞数");
        ConfigDO likes = configService.get(param);
        ModelAndView mav = new ModelAndView("admin/home/index");
        mav.addObject("homeViewCount", home.getValue());
        mav.addObject("caseViewCount", cases.getValue());
        mav.addObject("productLikeCount", likes.getValue());
        return mav;
    }
    @Autowired
    private ConfigService configService;
    private CaseService caseService;
}
