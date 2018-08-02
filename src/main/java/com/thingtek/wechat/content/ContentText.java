package com.thingtek.wechat.content;

import lombok.Data;

public @Data
class ContentText {

    private String wechatId;

    private String onReceiveText;
    private String onReceiveVoice;
    private String onReceiveImage;
    private String onReceiveShortvideo;
    private String onReceiveVideo;
    private String onReceiveLocation;
    private String onReceiveLink;
    private String onSubscriptionEvent;
    private String onUnSubscriptionEvent;
    private String onScanWithSubscribeEvent;
    private String onScanWithSubscribedEvent;
    private String onClickMenuEvent;
    private String onLocationEvent;
    private String onLocationSelectEvent;
    private String onPicWeixinEvent;
    private String onPicPhotoOrAlbumnEvent;
    private String onPicSysPhotoEvent;
    private String onScanCodeWaitMsgEvent;
    private String onScanCodePushEvent;
    private String onViewMenuEvent;
    private String onMassSendJobFinishEvent;


}
