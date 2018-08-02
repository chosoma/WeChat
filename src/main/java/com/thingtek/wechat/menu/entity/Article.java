package com.thingtek.wechat.menu.entity;

import lombok.Data;

public @Data
class Article {

    private String article_id;
    private Integer num;
    private String title;
    private String pic_url;
    private String url;
    private String description;

}
