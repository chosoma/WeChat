package com.thingtek.project_view.controller;


import com.thingtek.base.controller.BaseController;
import com.thingtek.project_view.entity.CueMessage;
import com.thingtek.company.entity.CompanyBean;
import com.thingtek.company.service.CompanyService;
import com.thingtek.project_view.service.ProjectService;
import com.thingtek.util.jsondate.JsonDateValueProcessorUtil;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController extends BaseController{

    @Resource
    private CompanyService companyService;

    @Resource
    private ProjectService projectService;

    @RequestMapping(value = "/join.action")
    public ModelAndView join() {
        //返回：输入秘钥的页面
        ModelAndView mav = new ModelAndView("pro/join");
        mav.addObject("message", new CueMessage());
        return mav;
    }

    @RequestMapping(value = "/check.action")
    public ModelAndView check(@RequestParam Map<String, String> code) throws IOException, ServletException {
        System.out.println("check.action");
        String key = code.get("key");
        System.out.println("code:" + code);

        //检查秘钥 并获取用户
        CompanyBean company = companyService.checkCdkey(key);
        System.out.println(company);

        ModelAndView mav = new ModelAndView();
        if (company != null) {
            // 秘钥检查成功：返回数据页面并将用户名绑定到页面
            mav.addObject("company", company);
            mav.setViewName("pro/" + company.getPro_name());
            return mav;
        } else {
            //秘钥检查失败：只返回错误消息
            CueMessage message = new CueMessage();
            message.setFlag(true);//是否警告
            message.setTitle("警告");//警告标题
            message.setBtnval("确定");//警告按钮消息
            //警告信息
            if (key == null || key.equals("")) {
                message.setMessage("请输入有效信息");
            } else {
                message.setInfo(key);//将用户输入的cdkey绑定到页面
                message.setMessage("输入有误!");
            }
            //将消息绑定到页面
            mav.addObject("message", message);
            // 秘钥检查失败：返回检查页面
            mav.setViewName("pro/join");
        }
        return mav;
    }


    @RequestMapping(value = "/data.action")
    public ModelAndView data(HttpServletResponse response, @RequestParam Map<String, String> map) throws IOException {
        System.out.println("data.action");
        //确认用户是否进行cdkey认证

        if (companyService.getCompany(map) == null) {
            //没有认证 返回 登录入口
            return join();
        } else {
            //认证 返回对应用户项目工程数据
            JsonConfig jsonConfig = new JsonConfig();
            jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessorUtil());
            JSONArray json = JSONArray.fromObject(projectService.getDatas(map), jsonConfig);
            response.getWriter().write(json.toString());
        }
        return null;
    }

}
