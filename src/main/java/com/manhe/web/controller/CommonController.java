package com.manhe.web.controller;

import com.alibaba.fastjson.JSON;
import com.manhe.common.Response;
import com.manhe.common.StringUtils;
import com.manhe.dal.dataobject.CaseDO;
import com.manhe.dal.dataobject.NewsDO;
import com.manhe.dal.dataobject.ProductCategoryDO;
import com.manhe.service.CaseService;
import com.manhe.service.NewsService;
import com.manhe.service.ProductCategoryService;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class CommonController {
    @RequestMapping("/uploadImage")
    @ResponseBody
    public Response editMovieInfo(@RequestParam("file") MultipartFile file, @Ignore Response response) throws IOException {
        String name="";
        response.setError("图片上传失败");
        if (file != null) {
            name = file.getOriginalFilename();//直接返回文件的名字
            String subffix = name.substring(name.lastIndexOf(".") + 1, name.length());//我这里取得文件后缀
            String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//文件保存进来，我给他重新命名，数据库保存有原本的名字，所以输出的时候再把他附上原本的名字就行了。
            String filepath="C:\\manhe\\static\\upload\\";//
            File newFile=new File(filepath);
            file.transferTo(new File(newFile+"\\"+fileName+"."+subffix));//保存文件
            response.setData("/upload/"+fileName+"."+subffix);
            response.setSuccess(true);
        }

        return response;
    }

    @Autowired
    private CaseService caseService;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private NewsService newsService;
}
