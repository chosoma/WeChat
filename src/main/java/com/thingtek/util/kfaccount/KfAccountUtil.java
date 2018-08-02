package com.thingtek.util.kfaccount;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.KfAccount;
import com.soecode.wxtools.bean.KfSender;
import com.soecode.wxtools.bean.SenderContent;
import com.soecode.wxtools.bean.result.KfAccountListResult;
import com.soecode.wxtools.bean.result.WxError;
import com.soecode.wxtools.exception.WxErrorException;

import java.io.File;

public class KfAccountUtil {

    /* ---addKfAccount 添加客服--- */
    public WxError addKfAccount(IService iService) throws WxErrorException {
        WxError result = iService.addKfAccount(new KfAccount("ant@test", "ant", "ant"));
        System.out.println(result);
        return result;
    }

    /* ---updateKfAccount 更新客服--- */
    public WxError updateKfAccount(IService iService) throws WxErrorException {
        WxError result = iService.updateKfAccount(new KfAccount("ant@test", "ant", "ant"));
        System.out.println(result);
        return result;
    }

    /* ---deleteKfAccount 删除客服--- */
    public WxError deleteKfAccount(IService iService) throws WxErrorException {
        WxError result = iService.deleteKfAccount(new KfAccount("ant@test", "ant", "ant"));
        System.out.println(result);
        return result;
    }

    /* ---getAllKfAccount 获取所有客服信息--- */
    public KfAccountListResult getAllKfAccount(IService iService) throws WxErrorException {
        KfAccountListResult result = iService.getAllKfAccount();
        System.out.println(result);
        return result;
    }

    /* ---sendMessageByKf 客服发送消息--- */
    public WxError sendMessageByKf(IService iService) throws WxErrorException {
        KfSender sender = new KfSender();
        sender.setTouser("oROCnuNihJnO9bnKOAORDFFriPgQ");
        sender.setMsgtype(WxConsts.MASS_MSG_TEXT);
        sender.setText(new SenderContent.Text("hi"));
        WxError result = iService.sendMessageByKf(sender);
        System.out.println(result);
        return result;
    }

    /* ---updateKfHeadImage 更新客服头像--- */
    public WxError updateKfHeadImage(IService iService) throws WxErrorException {
        WxError result = iService.updateKfHeadImage("oROCnuNihJnO9bnKOAORDFFriPgQ", new File("D:/wx/wx.jpg"));
        System.out.println(result);
        return result;
    }
}
