package com.thingtek.wechat.templateinfo.dao;

import com.thingtek.base.dao.BaseDao;
import com.thingtek.wechat.templateinfo.entity.TemplateInfo;

import java.sql.SQLException;

public interface TemplateInfoDao extends BaseDao{

    TemplateInfo getTemplateInfoByProName(String pro_name) throws SQLException;

}
