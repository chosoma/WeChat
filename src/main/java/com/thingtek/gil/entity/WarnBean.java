package com.thingtek.gil.entity;

import com.thingtek.base.entity.BaseBean;
import lombok.Data;

import java.util.Date;

public @Data
class WarnBean extends BaseBean {

    private int id;
    private String info;
    private String handle;
    private Date date;
    private String place;
    private String xw;
    private int warn = 2;

}
