package com.thingtek.wechat.menu.dao;

import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.thingtek.wechat.menu.entity.Article;
import com.thingtek.wechat.menu.entity.MenuConfig;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

public interface MenuConfigDao {

    List<MenuConfig> findAllMenuConfig() throws SQLException;

    List<Article> findAllArticlesById(MenuConfig menu_config)throws SQLException;

    boolean saveMenuConfig(MenuConfig menuConfig)throws SQLException;

    boolean saveArticle(Article article)throws SQLException;


}
