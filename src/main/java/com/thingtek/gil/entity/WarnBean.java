package com.thingtek.gil.entity;

import com.thingtek.base.entity.BaseBean;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
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
