package com.thingtek.gil.entity;

import lombok.Data;

public @Data
class PageBean {

    private Integer pageNum;

    private Integer pageStart;

    private Integer pageSize;

    private Integer unitType;

    private String dateStart;

    private String dateEnd;

}
