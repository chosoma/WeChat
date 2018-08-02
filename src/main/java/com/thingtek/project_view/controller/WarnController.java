package com.thingtek.project_view.controller;


import com.soecode.wxtools.exception.WxErrorException;
import com.thingtek.project_view.entity.WarnBean;
import com.thingtek.user.entity.UserBean;
import com.thingtek.user.service.UserService;
import com.thingtek.wechat.service.WxService;
import com.thingtek.wechat.templateinfo.entity.TemplateInfo;
import com.thingtek.wechat.templateinfo.service.TemplateInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class WarnController {

    @Resource
    private UserService userService;

    @Resource
    private WxService wxService;

    @Resource
    private TemplateInfoService templateService;

    @RequestMapping("/warn.action")
    public void warn(WarnBean warnBean) {
        System.out.println("warnings");
        System.out.println(warnBean);

        List<UserBean> users = userService.findUserByProName(warnBean.getPro_name());

        TemplateInfo template = templateService.getTemplateInfo(warnBean.getPro_name());
        if (template == null) {
            return;
        }
        /*
                以下应有消息推送
         */
        //群发消息 ---> 等待认证测试

        wxService.sendAllByOpenid(users, warnBean);


        //客服消息


        //模板消息

        wxService.sendTemplateByOpenid(users, warnBean, template);


    }


}
