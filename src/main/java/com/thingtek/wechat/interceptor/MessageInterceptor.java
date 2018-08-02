package com.thingtek.wechat.interceptor;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageInterceptor;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.exception.WxErrorException;
import org.springframework.stereotype.Component;

import java.util.Map;

/*
   拦截器
 */
@Component
public class MessageInterceptor implements WxMessageInterceptor {
    @Override
    public boolean intercept(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) throws WxErrorException {
        return true;
    }
}
