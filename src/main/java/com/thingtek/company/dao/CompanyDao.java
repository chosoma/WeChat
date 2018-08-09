package com.thingtek.company.dao;


import com.thingtek.base.dao.BaseDao;
import com.thingtek.company.entity.CompanyBean;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDao extends BaseDao {

    List<CompanyBean> findCompanyByCompanyname(String companyname) throws SQLException;// 获取用户所有工程  不建议使用

    CompanyBean findCompanyByCdkey(String pro_cdkey) throws SQLException;

    CompanyBean findCompanyByProname(String pro_name) throws SQLException;

}
