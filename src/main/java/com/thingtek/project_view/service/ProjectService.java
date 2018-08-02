package com.thingtek.project_view.service;

import com.thingtek.base.entity.BaseBean;
import com.thingtek.base.service.BaseService;
import com.thingtek.gil.dao.GILDao;
import com.thingtek.gil.entity.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService extends BaseService {

    @Resource
    private GILDao gilDao;

    /*
        根据页面请求的相关信息获取数据
     */
    public List<BaseBean> getDatas(Map<String, String> map) {
        System.out.println("getDatas");
        PageBean pageBean = new PageBean();
        //每页数据条数
        String strPageSize = map.get("pageSize");
        pageBean.setPageSize(regNum(strPageSize) ? Integer.parseInt(strPageSize) : 20);
        //起始数
        String strPageStart = map.get("pageStart");
        pageBean.setPageStart(regNum(strPageStart) ? Integer.parseInt(strPageStart) : 1);

        String dateStart = map.get("dateStart");
        pageBean.setDateStart(isNotNull(dateStart) ? dateStart : null);

        String dateEnd = map.get("dateEnd");
        pageBean.setDateEnd(isNotNull(dateEnd) ? dateEnd : null);

        //单元类型
        String strType = map.get("unitType");
        Integer type;
        pageBean.setUnitType(type = regNum(strType) ? Integer.parseInt(strType) : 1);
        try {
            return type == 5 ? gilDao.findWarn(pageBean) : type > 0 && type < 5 ? gilDao.findByType(pageBean) : new ArrayList<BaseBean>();
        } catch (SQLException e) {
            log(e);
            return new ArrayList<>();
        }
    }

    private boolean isNotNull(String str) {
        return str != null && !str.equals("");
    }

    //检查字符串是否可转化为数字
    private boolean regNum(String strnum) {
        return strnum != null && strnum.matches("\\d+");
    }


}
