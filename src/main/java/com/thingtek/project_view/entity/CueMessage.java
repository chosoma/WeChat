package com.thingtek.project_view.entity;

import lombok.Data;

public@Data
class CueMessage {
    private boolean flag; //是否警告 默认false
    private String message; //警告信息
    private String title; //警告标题
    private String info; //警告信息
    private String btnval; //警告按钮
}
