package com.thingtek.project_view.entity;

import lombok.Data;

import java.util.Date;

public @Data
class WarnBean {

    private String pro_name;//工程名

    private String warn_info;//报警信息

    private Date time;//时间

}
