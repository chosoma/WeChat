package com.thingtek.user.entity;

import lombok.Data;

import java.util.Date;

public @Data
class UserBean {

    private Integer id;

    private String user_id;

    private String pro_name;

    private Boolean subjective;

    private Date follow_time;

    private Boolean follow_state;

    private Date bind_time;

    private Date debind_time;

    private Boolean bind_state;


}
