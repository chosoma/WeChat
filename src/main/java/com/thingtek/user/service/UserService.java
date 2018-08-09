package com.thingtek.user.service;


import com.thingtek.base.service.BaseService;
import com.thingtek.user.dao.UserDao;
import com.thingtek.user.entity.UserBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class UserService extends BaseService {

    @Resource
    private UserDao dao;

    public boolean follow(UserBean userBean) {
        try {
            return dao.follow(userBean);
        } catch (SQLException e) {
            logException(e);
            return false;
        }
    }

    public boolean saveBind(UserBean userBean) {
        try {
            return dao.saveBind(userBean);
        } catch (SQLException e) {
            logException(e);
            return false;
        }
    }

    public List<UserBean> findUserByUserId(String user_id) {
        UserBean userBean = new UserBean();
        userBean.setUser_id(user_id);
        try {
            return dao.findUserByUserId(userBean);
        } catch (SQLException e) {
            logException(e);
            return new ArrayList<>();
        }
    }

    public List<UserBean> findUserByProName(String pro_name) {
        UserBean userBean = new UserBean();
        userBean.setPro_name(pro_name);
        try {
            return dao.findUserByProName(userBean);
        } catch (SQLException e) {
            logException(e);
            return new ArrayList<>();
        }
    }


    public UserBean findSubUserByUserId(String user_id) {
        List<UserBean> userBeans = findUserByProName(user_id);
        for (UserBean user : userBeans) {
            if (user.getSubjective()) {
                return user;
            }
        }
        if (userBeans.size() > 0) {
            return userBeans.get(0);
        }
        return null;
    }

    public void resouceUser(UserBean user, Map<String, String> map) {
//        user.setUser_id(map.get("user_id"));
        user.setPro_name(map.get("pro_name"));
    }

    public boolean updateBind(UserBean userBean) {
        try {
            return dao.reinitBind(userBean) && dao.updateBind(userBean);
        } catch (SQLException e) {
            logException(e);
            return false;
        }
    }

}
