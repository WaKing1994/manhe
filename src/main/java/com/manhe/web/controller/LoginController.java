package com.manhe.web.controller;

import com.manhe.dal.dataobject.AdminDO;
import com.manhe.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @GetMapping("/admin/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/admin/loginVerify")
    public String loginVerify(String username, String password, HttpSession session) {
        HttpServletRequest request = getNativeRequest();

        Map<String, Object> param = new HashMap<>();
        param.put("username", username);
        param.put("password", password);
        AdminDO adminDO = adminService.get(param);

        if (adminDO != null) {
            session = request.getSession(true);
            session.setAttribute("user", adminDO);
            return "admin/home/index";
        } else {
            return "admin/login";
        }

    }

    private HttpServletRequest getNativeRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    @Autowired
    private AdminService adminService;
}
