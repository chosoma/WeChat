package com.thingtek.company.service;

import com.thingtek.base.service.BaseService;
import com.thingtek.company.dao.CompanyDao;
import com.thingtek.company.entity.CompanyBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Map;

@Service
public class CompanyService extends BaseService {
    @Resource
    private CompanyDao companyDao;

    public CompanyBean checkCdkey(String cd_key) {
        try {
            return companyDao.findCompanyByCdkey(cd_key);
        } catch (SQLException e) {
            log(e);
        }
        return null;
    }

    public CompanyBean getCompany(Map<String, String> map) {
        String pro_name = map.get("pro_name");
        try {
            return pro_name != null ? companyDao.findCompanyByProname(pro_name) : null;
        } catch (SQLException e) {
            log(e);
        }
        String pro_cdkey = map.get("pro_cdkey");
        return pro_cdkey != null ? checkCdkey(pro_cdkey) : null;
    }

}

