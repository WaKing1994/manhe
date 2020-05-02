package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.service.CaseCategoryService;
import com.manhe.service.CaseService;
import com.manhe.service.NewsService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController("adminNewsController")
@RequestMapping("/admin/news")
public class NewsController {
    @RequestMapping("/list")
    public ModelAndView list() {
        List<NewsDO> newsDOS = newsService.find(null);
        ModelAndView mav = new ModelAndView("admin/news/list");
        mav.addObject("news", newsDOS);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("admin/news/add");
        return mav;
    }

    @PostMapping("/addSubmit")
    public Response addSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        /**
         * todo
         * */
        return response;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        NewsDO newsDO = newsService.get(param);
        ModelAndView mav = new ModelAndView("admin/news/edit");
        mav.addObject("news", newsDO);
        return mav;
    }

    @PostMapping("/editSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        /**
         * todo
         * */
        return response;
    }

    @PostMapping("/delete")
    public Response delete(Long id, @Ignore Response response) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        newsService.delete(param);
        return response;
    }


    @Autowired
    private CaseCategoryService caseCategoryService;
    @Autowired
    private CaseService caseService;
    @Autowired
    private NewsService newsService;

}
