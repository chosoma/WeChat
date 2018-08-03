package com.thingtek.project_view.controller;

import com.soecode.wxtools.bean.WxUserList;
import com.thingtek.base.controller.BaseController;
import com.thingtek.user.entity.UserBean;
import com.thingtek.user.service.UserService;
import com.thingtek.wechat.service.WxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller("/pro")
public class BindController extends BaseController {

    @Resource
    private WxService wxService;

    @Resource
    private UserService userService;

    @RequestMapping("/bindhome.action")
    public void userbindhome(String code) {

        WxUserList.WxUser wxuser = wxService.getUserInfoByCode(code);
        List<UserBean> userList = userService.findUserByUserId(wxuser.getOpenid());




    }

    @RequestMapping("/bind.action")
    public void userbind() {

    }


    /*
            添加绑定工程
     */
    @RequestMapping("/addbind.action")
    public void addbind(Map<String, Object> map) {
        UserBean userBean = userService.resouceUser(map);
        userBean.setBind_time(new Date());
        userService.saveBind(userBean);



    }

    /*
            修改绑定工程
     */
    @RequestMapping("/editbind.action")
    public void editbind(Map<String, Object> map) {
        UserBean userBean = userService.resouceUser(map);
        userBean.setSubjective(true);
    }

    /*
            删除绑定工程
     */
    @RequestMapping("/delbind.action")
    public void delbind(Map<String, Object> map) {

    }





}
