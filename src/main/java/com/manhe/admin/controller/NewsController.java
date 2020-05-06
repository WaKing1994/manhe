package com.manhe.admin.controller;

import com.manhe.common.Response;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.dal.pageUtils.PageInfo;
import com.manhe.service.CaseCategoryService;
import com.manhe.service.CaseService;
import com.manhe.service.NewsService;
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
    @PostMapping("/list2")
    public Map<String,Object> list(@Param("limits") Integer limits, @Param("page") Integer page, @Ignore Response response) {
        PageInfo pageInfo = PageInfo.genPageInfoPage(page == null ? 1 : page, limits == null ? 10 : limits);
        List<NewsDO> newsDOS = newsService.find(null, pageInfo);
        Map<String,Object> resultMap = new HashMap<String, Object>();

        resultMap.put("code",0);
        resultMap.put("msg","");
        resultMap.put("count",pageInfo.getTotalCount());
        resultMap.put("data",newsDOS);

        return resultMap;
    }
    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = new ModelAndView("admin/news/add");
        return mav;
    }

    @PostMapping("/addSubmit")
    public Response addSubmit(@RequestBody Map<String, Object> param, @Ignore Response response) {
        Map<String, Object> req = (HashMap) param.get("field");
        String name = req.get("name").toString();
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        String banner = req.get("banner").toString();
        NewsDO newsDO = new NewsDO();
        newsDO.setName(name);
        newsDO.setDetails(details);
        newsDO.setPriority(priority);
        newsDO.setBanner(banner);
        newsDO.setViewCount(0);
        newsService.insert(newsDO);
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
        Map<String, Object> req = (HashMap) param.get("field");
        Long id = Long.valueOf(req.get("id").toString());
        String name = req.get("name").toString();
        String details = req.get("details").toString();
        Integer priority = Integer.valueOf(req.get("priority").toString());
        Integer viewCount = Integer.valueOf(req.get("viewCount").toString());
        String banner = req.get("banner").toString();
        NewsDO newsDO = new NewsDO();
        newsDO.setId(id);
        newsDO.setName(name);
        newsDO.setDetails(details);
        newsDO.setPriority(priority);
        newsDO.setBanner(banner);
        newsDO.setViewCount(viewCount);
        newsService.update(newsDO);
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
