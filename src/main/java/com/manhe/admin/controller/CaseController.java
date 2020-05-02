package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.CaseCategoryDO;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.service.CaseCategoryService;
import com.manhe.service.CaseService;
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

@RestController("adminCaseController")
@RequestMapping("/admin/case")
public class CaseController {
    @RequestMapping("/list")
    public ModelAndView list() {
        List<CaseDO> caseDOS = caseService.find(null);
        ModelAndView mav = new ModelAndView("admin/case/list");
        mav.addObject("cases", caseDOS);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("admin/case/add");
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
        CaseDO caseDO = caseService.get(param);
        ModelAndView mav = new ModelAndView("admin/case/edit");
        mav.addObject("case", caseDO);
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
        caseService.delete(param);
        return response;
    }


    @Autowired
    private CaseCategoryService caseCategoryService;
    @Autowired
    private CaseService caseService;

}
