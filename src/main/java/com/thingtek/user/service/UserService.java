package com.thingtek.user.service;


import com.thingtek.base.service.BaseService;
import com.thingtek.user.dao.UserDao;
import com.thingtek.user.entity.UserBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserService extends BaseService {

    @Resource
    private UserDao dao;

    public boolean follow(UserBean... userBeans) {
        try {
            return dao.follow(Arrays.asList(userBeans));
        } catch (SQLException e) {
            log(e);
            return false;
        }
    }

    public boolean saveBind(UserBean... userBeans) {
        try {
            return dao.saveBind(Arrays.asList(userBeans));
        } catch (SQLException e) {
            log(e);
            return false;
        }
    }

    public List<UserBean> findUserByUserId(String user_id) {
        UserBean userBean = new UserBean();
        userBean.setUser_id(user_id);
        try {
            return dao.findUserByUserId(userBean);
        } catch (SQLException e) {
            log(e);
            return new ArrayList<>();
        }
    }

    public List<UserBean> findUserByProName(String pro_name) {
        UserBean userBean = new UserBean();
        userBean.setPro_name(pro_name);
        try {
            return dao.findUserByProName(userBean);
        } catch (SQLException e) {
            log(e);
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
}
