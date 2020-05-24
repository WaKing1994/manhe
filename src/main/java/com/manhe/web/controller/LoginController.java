package com.manhe.web.controller;

import com.manhe.dal.dataobject.AdminDO;
import com.manhe.dal.dataobject.ConfigDO;
import com.manhe.service.AdminService;
import com.manhe.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping("/admin/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("admin/login");
        return mav;
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        ModelAndView mav = new ModelAndView("admin/login");
        return mav;
    }

    @PostMapping("/admin/loginVerify")
    public ModelAndView loginVerify(String username, String password, HttpSession session) {
        HttpServletRequest request = getNativeRequest();

        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password", password);
        AdminDO adminDO = adminService.get(param);

        if (adminDO != null) {
            session = request.getSession(true);
            session.setAttribute("user", adminDO);

            Map<String, Object> configParam = new HashMap<>();
            configParam.put("name", "首页浏览数");
            ConfigDO home = configService.get(configParam);
            configParam.put("name", "工程浏览数");
            ConfigDO cases = configService.get(configParam);
            ModelAndView mav = new ModelAndView("redirect:home/index");
            mav.addObject("homeViewCount", home.getValue());
            mav.addObject("caseViewCount", cases.getValue());
            return mav;
        } else {
            ModelAndView mav = new ModelAndView("redirect:login");
            return mav;
        }

    }

    private HttpServletRequest getNativeRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    @Autowired
    private AdminService adminService;
    @Autowired
    private ConfigService configService;
}
