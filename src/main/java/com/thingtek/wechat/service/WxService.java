package com.thingtek.wechat.service;


import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.SenderContent;
import com.soecode.wxtools.bean.TemplateSender;
import com.soecode.wxtools.bean.WxOpenidSender;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.WxErrorException;
import com.thingtek.project_view.entity.WarnBean;
import com.thingtek.user.entity.UserBean;
import com.thingtek.wechat.templateinfo.entity.TemplateInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WxService extends com.soecode.wxtools.api.WxService {

    /*
        根据code获取用户信息
     */
    public WxUserList.WxUser getUserInfoByCode(String code) {
        try {
            WxOAuth2AccessTokenResult result = this.oauth2ToGetAccessToken(code);
            return this.oauth2ToGetUserInfo(result.getRefresh_token(),
                    new WxUserList.WxUser.WxUserGet(result.getOpenid(), WxConsts.LANG_CHINA));
        } catch (WxErrorException e) {
            return null;
        }
    }


    public void sendAllByOpenid(List<UserBean> users, WarnBean warn) {
        List<String> touser = new ArrayList<>();
        for (UserBean userBean : users) touser.add(userBean.getUser_id());

        WxOpenidSender openidSender = new WxOpenidSender();
        openidSender.setTouser(touser);
        SenderContent.Text text = new SenderContent.Text();
        text.setContent(warn.getWarn_info());
        openidSender.setText(text);
        openidSender.setMsgtype(WxConsts.SEND_ALL_TEXT);
        try {
            sendAllByOpenid(openidSender);
        } catch (WxErrorException e) {
            log(e);
        }
    }

    public void sendTemplateByOpenid(List<UserBean> users, WarnBean warn, TemplateInfo template) {
        for (UserBean user : users) {
            //模板消息
            TemplateSender sender = new TemplateSender();
            sender.setTemplate_id(template.getTemplate_id());//设置模板ID
            sender.setTouser(user.getUser_id());//用户名
            sender.setUrl(template.getUrl());//详情url
            try {
                sender.setData(this.getClass()
                        .getClassLoader()
                        .loadClass(template.getClazz())
                        .getConstructor(WarnBean.class)
                        .newInstance(warn));
            } catch (ReflectiveOperationException e) {
                log(e);
                continue;
            }

            try {
                this.templateSend(sender);
            } catch (WxErrorException e) {
                log(e);
            }
        }
    }

    public void log(Exception e) {
        e.printStackTrace();
    }

}
