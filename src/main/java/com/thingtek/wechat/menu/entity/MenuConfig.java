package com.thingtek.wechat.menu.entity;

import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import lombok.Data;

import java.util.List;

public @Data
class MenuConfig {
    private String menu_key;
    private String message_type;
    private String content;
    private String media_id;
    private String article_id;
    private String file_url;
    private List<WxXmlOutNewsMessage.Item> articles;

}
