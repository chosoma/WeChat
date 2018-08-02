package com.thingtek.wechat.controller;

import com.soecode.wxtools.api.WxConfig;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.handler.DemoHandler;
import com.soecode.wxtools.interceptor.DemoInterceptor;
import com.soecode.wxtools.matcher.DemoMatcher;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.thingtek.wechat.handler.MessageHandler;
import com.thingtek.wechat.interceptor.MessageInterceptor;
import com.thingtek.wechat.matcher.MessageMatcher;
import com.thingtek.wechat.service.WxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class WxController {

    @Resource
    private WxService service;

    @Resource
    private MessageHandler handler;
    @Resource
    private MessageInterceptor interceptor;
    @Resource
    private MessageMatcher matcher;

    @Resource(name = "menu")
    private WxMenu menu;

    @RequestMapping(value = "/wx.action", method = RequestMethod.GET)
    public void wxGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        long start = System.currentTimeMillis();
        PrintWriter out = response.getWriter();
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        if (service.checkSignature(signature, timestamp, nonce, echostr)) {
            out.print(echostr);
//            service.createMenu(menu, false);
        }
        long end = System.currentTimeMillis();
        System.out.println("Token验证耗时:" + (end - start) + "ms");
    }

    @RequestMapping(value = "/wx.action", method = RequestMethod.POST)
    public void wxPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String encrypt_type = request.getParameter("encrypt_type");
        WxMessageRouter router = new WxMessageRouter(service);
        System.err.println("encrypt_type:" + encrypt_type);
        if (encrypt_type != null && "aes".equals(encrypt_type)) {
//				String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String msg_signature = request.getParameter("msg_signature");

            WxXmlMessage wx = WxXmlMessage.decryptMsg(request.getInputStream(), WxConfig.getInstance(), timestamp,
                    nonce, msg_signature);
            System.out.println("Message：\n " + wx.toString());
            router.rule().matcher(new DemoMatcher()).interceptor(new DemoInterceptor()).handler(new DemoHandler()).end();
            WxXmlOutMessage xmlOutMsg = router.route(wx);
            if (xmlOutMsg != null) {
                out.print(WxXmlOutMessage.encryptMsg(WxConfig.getInstance(), xmlOutMsg.toXml(), timestamp, nonce));
            }
        } else {
            WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
            System.out.println("接收的Message：\n " + wx.toString());
            service.getAccessToken();
            router.rule().matcher(matcher).interceptor(interceptor).handler(handler).end();
            WxXmlOutMessage xmlOutMsg = router.route(wx);
            if (xmlOutMsg != null) {
                String xml = xmlOutMsg.toXml();
                System.out.println("发送的Message:\n" + xml);
                out.print(xmlOutMsg.toXml());
            }
        }
    }

    @RequestMapping("/menu.action")
    public void menu() throws WxErrorException {
        service.createMenu(menu, false);
    }


}
