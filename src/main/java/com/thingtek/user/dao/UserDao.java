package com.thingtek.user.dao;


import com.thingtek.user.entity.UserBean;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {


    boolean follow(List<UserBean> userBeans) throws SQLException;

    boolean saveBind(List<UserBean> userBeans) throws SQLException;

    List<UserBean> findUserByUserId(UserBean userBean) throws SQLException;

    List<UserBean> findUserByProName(UserBean userBean) throws SQLException;

    boolean saveDeBind(List<UserBean> userBeans)throws SQLException;

    boolean delBind(UserBean userBean) throws SQLException;


}
