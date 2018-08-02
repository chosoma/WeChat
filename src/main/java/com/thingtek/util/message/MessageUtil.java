package com.thingtek.util.message;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.bean.*;
import com.soecode.wxtools.bean.result.*;
import com.soecode.wxtools.exception.WxErrorException;
import com.thingtek.user.entity.UserBean;

import java.util.ArrayList;
import java.util.List;

public class MessageUtil {

    /* ---通过用户标签来群发--- */
    public void sendAllByTag(IService iService) throws WxErrorException {
        WxTagSender sender = new WxTagSender();
        //设置哪些组需要接受群发
        sender.setFilter(new SenderFilter(true, 1));
        //群发文本内容
        sender.setText(new SenderContent.Text("文本内容"));
        SenderResult result = iService.sendAllByTag(sender);
        System.out.println(result.toString());
        /*如果发其他类型的消息:
        //群发图片，以此类推
        sender.setImage(new SenderContent.Media("media_id"));*/
    }

    /* ---针对某群人的openid群发--- */
    public void sendAllByOpenid(IService iService, String info, UserBean... users) throws WxErrorException {
        WxOpenidSender sender = new WxOpenidSender();
        List<String> openidList = new ArrayList<>();
        for (UserBean user : users) {
            openidList.add(user.getUser_id());
        }
//        openidList.add("openid1");
//        openidList.add("openid2");
        sender.setTouser(openidList);
        //群发文本内容
        sender.setText(new SenderContent.Text(info));
        SenderResult result = iService.sendAllByOpenid(sender);
        System.out.println(result.toString());
    }

    /* ---群发预览--- */
    public void sendAllPreview(IService iService) throws WxErrorException {
        PreviewSender sender = new PreviewSender();
        //设置openid或者微信号，优先级为wxname高
        sender.setTouser("openid");
        sender.setTowxname("微信号");
        //群发文本内容
        sender.setText(new SenderContent.Text("文本内容"));
        SenderResult result = iService.sendAllPreview(sender);
        System.out.println(result.toString());
    }

    /* ---删除群发--- */
    public void sendAllDelete(IService iService) throws WxErrorException {
        //msg_id参数在群发接口中返回
        SenderResult result = iService.sendAllDelete("msg_id");
        System.out.println(result.toString());
    }

    /* ---获取群发状态--- */
    public void sendAllGetStatus(IService iService) throws WxErrorException {
        SenderResult result = iService.sendAllGetStatus("msg_id");
        System.out.println(result.toString());
    }

    /* ---设置模板消息的行业--- */
    public void templateSetIndustry(IService iService) throws WxErrorException {
        //行业代码参考官方文档。
        iService.templateSetIndustry("2", "3");
    }

    /* ---获取模板消息的行业设置--- */
    public void templateGetIndustry(IService iService) throws WxErrorException {
        IndustryResult result = iService.templateGetIndustry();
        System.out.println(result.getPrimary_industry());
        System.out.println(result.getSecondary_industry());
    }

    /* ---通过短ID获取模板ID--- */
    public void templateGetId(IService iService) throws WxErrorException {
        //模板库中模板的编号，有“TM**”和“OPENTMTM**”等形式
        TemplateResult result = iService.templateGetId("template_id_short");
        System.out.println(result.toString());
    }

    /* ---获取模板列表--- */
    public void templateGetList(IService iService) throws WxErrorException {
        TemplateListResult result = iService.templateGetList();
        System.out.println(result.toString());
    }

    /* ---删除模板--- */
    public void templateDelete(IService iService) throws WxErrorException {
        iService.templateDelete("template_id");
    }

    /* ---模板消息发送--- */
    public void templateSend(IService iService) throws WxErrorException {
        TemplateSender sender = new TemplateSender();
        sender.setTouser("openid");
        sender.setTemplate_id("templateId");
        sender.setData("Object：与模板内容对应的对象");
        sender.setUrl("url");
        TemplateSenderResult result = iService.templateSend(sender);
        System.out.println(result.toString());
    }

}
