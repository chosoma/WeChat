package com.thingtek.gil.dao;


import com.thingtek.base.dao.BaseDao;
import com.thingtek.base.entity.BaseBean;
import com.thingtek.gil.entity.DataBean;
import com.thingtek.gil.entity.NetBean;
import com.thingtek.gil.entity.PageBean;
import com.thingtek.gil.entity.UnitBean;

import java.sql.SQLException;
import java.util.List;

public interface GILDao extends BaseDao{

    List<NetBean> findAllNets() throws SQLException;

    List<BaseBean> findDataByType(PageBean pageBean) throws SQLException;

    List<BaseBean> findWarn(PageBean pageBean) throws SQLException;


}
