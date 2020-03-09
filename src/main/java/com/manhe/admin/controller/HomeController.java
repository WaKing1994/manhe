package com.manhe.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("adminHomeController")
@RequestMapping("/admin/home")
public class HomeController {
    @GetMapping("/index")
    public String login() {
        return "/admin/home/index";
    }
}
