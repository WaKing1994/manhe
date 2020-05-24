package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.CaseCategoryDO;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.ProductDO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.CaseCategoryService;
import com.manhe.service.CaseService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.apache.ibatis.annotations.Param;
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

    @PostMapping("/list2")
    public Map<String, Object> list(@Param("name") String name, @Param("limits") Integer limits, @Param("page") Integer page, @Ignore Response response) {
        PageInfo pageInfo = PageInfo.genPageInfoPage(page == null ? 1 : page, limits == null ? 10 : limits);

        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        List<CaseDO> caseDOS = caseService.find(param, pageInfo);
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("code", 0);
        resultMap.put("msg", "");
        resultMap.put("count", pageInfo.getTotalCount());
        resultMap.put("data", caseDOS);

        return resultMap;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        List<CaseCategoryDO> caseCategoryDOS = caseCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/case/add");
        mav.addObject("caseCategory", caseCategoryDOS);
        return mav;
    }

    @PostMapping("/addSubmit")
    public Response addSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        String name = req.get("name").toString();
        Long categoryId = Long.valueOf(req.get("categoryId").toString());
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        String banner = req.get("banner").toString();
        CaseDO caseDO = new CaseDO();
        caseDO.setName(name);
        caseDO.setCategoryId(categoryId);
        caseDO.setDetails("  " + details);
        caseDO.setPriority(priority);
        caseDO.setBanner(banner);
        caseDO.setViewCount(0);
        caseService.insert(caseDO);
        return response;
    }

    @RequestMapping("/edit")
    public ModelAndView edit(Long id) {
        Map<String, Object> param = new HashMap<>();
        param.put("id", id);
        CaseDO caseDO = caseService.get(param);
        List<CaseCategoryDO> caseCategoryDOS = caseCategoryService.find(null);
        ModelAndView mav = new ModelAndView("admin/case/edit");
        mav.addObject("caseCategory", caseCategoryDOS);
        mav.addObject("case", caseDO);
        return mav;
    }

    @PostMapping("/editSubmit")
    public Response editSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        Long id = Long.valueOf(req.get("id").toString());
        String name = req.get("name").toString();
        Long categoryId = Long.valueOf(req.get("categoryId").toString());
        Integer viewCount = Integer.valueOf(req.get("viewCount").toString());
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        String banner = req.get("banner").toString();
        CaseDO caseDO = new CaseDO();
        caseDO.setId(id);
        caseDO.setName(name);
        caseDO.setCategoryId(categoryId);
        caseDO.setDetails("  " + details);
        caseDO.setPriority(priority);
        caseDO.setBanner(banner);
        caseDO.setViewCount(viewCount);
        caseService.update(caseDO);
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
