package com.manhe.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @GetMapping("/admin/login")
    public String login() {
        return "/admin/login";
    }

    @PostMapping("/admin/loginVerify")
    public String loginVerify(String username, String password, HttpSession session) {
        HttpServletRequest request = getNativeRequest();
        session = request.getSession(true);
        session.setAttribute("user", 12312312);
        if (username.equals("admin")) {
            return "/admin/home/index";
        } else {
            return "redirect:/admin/login";
        }

    }

    private HttpServletRequest getNativeRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

}
