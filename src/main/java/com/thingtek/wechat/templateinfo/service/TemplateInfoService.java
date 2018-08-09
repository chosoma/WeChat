package com.thingtek.wechat.templateinfo.service;

import com.thingtek.base.service.BaseService;
import com.thingtek.wechat.templateinfo.dao.TemplateInfoDao;
import com.thingtek.wechat.templateinfo.entity.TemplateInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;

@Service
public class TemplateInfoService extends BaseService {

    @Resource
    TemplateInfoDao dao;

    public TemplateInfo getTemplateInfo(String pro_name) {

        try {
            return dao.getTemplateInfoByProName(pro_name);
        } catch (SQLException e) {
            logException(e);
        }
        return null;
    }



}
