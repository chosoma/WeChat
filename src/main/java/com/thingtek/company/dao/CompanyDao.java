package com.thingtek.company.dao;


import com.thingtek.company.entity.CompanyBean;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao {

    CompanyBean findCompanyByCdkey(String pro_cdkey) throws SQLException;

    List<CompanyBean> findCompanyByCompanyname(String companyname) throws SQLException;// 获取用户所有工程  不建议使用

    CompanyBean findCompanyByProname(String pro_name) throws SQLException;

}
