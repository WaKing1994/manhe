package com.manhe.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manhe.common.Response;
import com.manhe.dal.dataobject.CaseCategoryDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.CaseCategoryService;
import com.manhe.service.ProductCategoryService;
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

@RestController("adminCaseCategoryController")
@RequestMapping("/admin/caseCategory")
public class CaseCategoryController {
    @RequestMapping("/list")
    public ModelAndView product() {

        List<CaseCategoryDO> caseCategoryDOS = caseCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/caseCategory/list");
        mav.addObject("caseCategorys", caseCategoryDOS);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("admin/caseCategory/add");
        return mav;
    }

    @PostMapping("/addSubmit")
    public Response addSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        //JSONObject field = (JSONObject) param.get("field");
        Map<String, Object> req = (HashMap) param.get("field");
        String name = req.get("name").toString();
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        CaseCategoryDO caseCategoryDO = new CaseCategoryDO();
        caseCategoryDO.setName(name);
        caseCategoryDO.setDetails(details);
        caseCategoryDO.setPriority(priority);
        caseCategoryService.insert(caseCategoryDO);
        return response;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        CaseCategoryDO caseCategoryDO = caseCategoryService.get(param);
        ModelAndView mav = new ModelAndView("admin/caseCategory/edit");
        mav.addObject("caseCategory", caseCategoryDO);
        return mav;
    }

    @PostMapping("/editSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        Long id = Long.valueOf(req.get("id").toString());
        String name = req.get("name").toString();
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        CaseCategoryDO caseCategoryDO = new CaseCategoryDO();
        caseCategoryDO.setId(id);
        caseCategoryDO.setName(name);
        caseCategoryDO.setDetails(details);
        caseCategoryDO.setPriority(priority);
        caseCategoryService.update(caseCategoryDO);
        return response;
    }

    @PostMapping("/delete")
    public Response delete(Long id, @Ignore Response response) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        caseCategoryService.delete(param);
        return response;
    }


    @Autowired
    private CaseCategoryService caseCategoryService;

}
