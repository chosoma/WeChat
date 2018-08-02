package com.thingtek.gil.service;


import com.thingtek.base.service.BaseService;
import com.thingtek.gil.dao.GILDao;
import com.thingtek.gil.entity.NetBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Service
public class GILService extends BaseService {

    @Resource
    private GILDao dao;

    public List<NetBean> find() {
        try {
            return dao.findAllNets();
        } catch (SQLException e) {
            log(e);
            return null;
        }
    }


}
