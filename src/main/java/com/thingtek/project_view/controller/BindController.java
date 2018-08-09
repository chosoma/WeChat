package com.thingtek.project_view.controller;

import com.thingtek.base.controller.BaseController;
import com.thingtek.company.entity.CompanyBean;
import com.thingtek.company.service.CompanyService;
import com.thingtek.project_view.entity.CueMessage;
import com.thingtek.user.entity.UserBean;
import com.thingtek.user.service.UserService;
import com.thingtek.wechat.service.WxService;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userbind")
public class BindController extends BaseController {

    @Resource
    private WxService wxService;

    @Resource
    private UserService userService;

    @Resource
    private CompanyService companyService;


    @RequestMapping("/bindhome.action")
    public ModelAndView userbindhome(HttpServletRequest request, HttpServletResponse response, String code) {
        System.out.println("userbindhome");

//        WxUserList.WxUser wxuser = wxService.getUserInfoByCode(code); //获取user
//        List<UserBean> userList = userService.findUserByUserId(wxuser.getOpenid());
        System.out.println("code:" + code);

        List<UserBean> userList = userService.findUserByUserId(code);

        System.out.println(userList);
        ModelAndView mav = new ModelAndView("userbind/bindhome");
        mav.addObject("userList", userList);

        UserBean user = new UserBean();
        user.setUser_id(code);

        request.getSession().setAttribute("userInfo", user);

        return mav;
    }

    @RequestMapping("/bind.action")
    public ModelAndView userbind(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("userbind");
        return userbindhome(request, response, "onKatvwFMBT5n2SONq_LI7LoBLto");
    }


    /*
            添加绑定工程
     */
    @RequestMapping("/addbind.action")
    public void addbind(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
        System.out.println("addbind,map:" + map);
//        System.out.println("userInfo:" + request.getSession().getAttribute("userInfo"));
        UserBean user = (UserBean) request.getSession().getAttribute("userInfo");
        userService.resouceUser(user, map);

        CompanyBean company = companyService.checkCdkey(map.get("cd_key"));
        CueMessage cueMessage = new CueMessage();
        cueMessage.setBtnval("确定");
        if (company == null) {
            cueMessage.setFlag(false);
            cueMessage.setTitle("失败");
            cueMessage.setMessage("设置失败,CD_KEY有误!");
        } else {
            user.setPro_name(company.getPro_name());
            user.setBind_state(true);
            userService.saveBind(user);
            cueMessage.setFlag(true);
            cueMessage.setTitle("成功");
            cueMessage.setMessage("设置成功!");
            cueMessage.setInfo(company.getPro_name());
        }

        try {
            response.setContentType("json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(cueMessage).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
            修改绑定工程
     */
    @RequestMapping("/editbind.action")
    public void editbind(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
        System.out.println("editbind,map:" + map);
//        System.out.println("userInfo:" + request.getSession().getAttribute("userInfo"));
        UserBean user = (UserBean) request.getSession().getAttribute("userInfo");
        userService.resouceUser(user, map);
        user.setSubjective(true);
        CueMessage cueMessage = new CueMessage();
        cueMessage.setBtnval("确定");
        if (userService.updateBind(user)) {
            cueMessage.setFlag(true);
            cueMessage.setTitle("成功");
            cueMessage.setMessage("设置成功!");
        } else {
            cueMessage.setFlag(false);
            cueMessage.setTitle("失败");
            cueMessage.setMessage("设置失败,请联系管理员或服务人员!");
        }
        try {
            response.setContentType("json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(cueMessage).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
            删除绑定工程
     */
    @RequestMapping("/delbind.action")
    public void delbind(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, String> map) {
        System.out.println("delbind,map:" + map);
//        System.out.println("userInfo:" + request.getSession().getAttribute("userInfo"));
        UserBean user = (UserBean) request.getSession().getAttribute("userInfo");
        userService.resouceUser(user, map);
        user.setBind_state(false);
        CueMessage cueMessage = new CueMessage();
        cueMessage.setBtnval("确定");
        if (userService.saveBind(user)) {
            cueMessage.setFlag(true);
            cueMessage.setTitle("成功");
            cueMessage.setMessage("删除成功!");
        } else {
            cueMessage.setFlag(false);
            cueMessage.setTitle("失败");
            cueMessage.setMessage("删除失败,请联系管理员或服务人员!");
        }
        try {
            response.setContentType("json;charset=UTF-8");
            response.getWriter().write(JSONObject.fromObject(cueMessage).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
