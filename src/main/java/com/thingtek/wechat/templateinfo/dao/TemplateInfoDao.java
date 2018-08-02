package com.thingtek.wechat.templateinfo.dao;

import com.thingtek.wechat.templateinfo.entity.TemplateInfo;

import java.sql.SQLException;

public interface TemplateInfoDao {

    TemplateInfo getTemplateInfoByProName(String pro_name) throws SQLException;

}
