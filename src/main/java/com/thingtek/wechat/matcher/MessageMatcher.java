package com.thingtek.wechat.matcher;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.thingtek.user.entity.UserBean;
import com.thingtek.user.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;


/*
   消息匹配器 匹配某些消息并过滤
 */
@Component
public class MessageMatcher implements WxMessageMatcher {


    @Resource
    private UserService userService;

    @Override
    public boolean match(WxXmlMessage message) {
        boolean flag = true;
        switch (message.getMsgType()) {
            case WxConsts.XML_MSG_EVENT:
                switch (message.getEvent()) {
                    case WxConsts.EVT_UNSUBSCRIBE:
                        flag = false;
                    case WxConsts.EVT_SUBSCRIBE:
                        UserBean userBean = new UserBean();
                        userBean.setUser_id(message.getFromUserName());
                        userBean.setFollow_time(new Date(message.getCreateTime()));
                        userBean.setFollow_state(flag);
                        userService.follow(userBean);
                        break;
                }
        }
        return flag;
    }

}
