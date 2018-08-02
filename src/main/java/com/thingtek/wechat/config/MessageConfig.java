package com.thingtek.wechat.config;

import com.soecode.wxtools.bean.WxMessage;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public@Data
class MessageConfig {

    private String messageType;

    private String mediaId;

    private List<WxMessage.WxArticle> articles ;

    private String content;

}
