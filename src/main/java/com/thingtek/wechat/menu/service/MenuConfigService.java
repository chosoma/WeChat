package com.thingtek.wechat.menu.service;

import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.thingtek.base.service.BaseService;
import com.thingtek.wechat.menu.dao.MenuConfigDao;
import com.thingtek.wechat.menu.entity.Article;
import com.thingtek.wechat.menu.entity.MenuConfig;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuConfigService extends BaseService {

    @Resource
    private MenuConfigDao dao;

    private Map<String, MenuConfig> menuConfigMap = new HashMap<>();

    private List<MenuConfig> getMenuConfigs() {
        List<MenuConfig> menuConfigs = null;
        try {
            menuConfigs = dao.findAllMenuConfig();
        } catch (SQLException e) {
            logException(e);
            return null;
        }
        for (MenuConfig menuconfig : menuConfigs) {
            if (WxConsts.XML_MSG_NEWS.equals(menuconfig.getMessage_type())) {
                addArticles(menuconfig);
            }
        }
        for (MenuConfig config : menuConfigs) {
            menuConfigMap.put(config.getMenu_key(), config);
        }
        return menuConfigs;
    }

    private void addArticles(MenuConfig menuconfig) {
        List<Article> articles = null;
        try {
            articles = dao.findAllArticlesById(menuconfig);
        } catch (SQLException e) {
            logException(e);
        }
        List<WxXmlOutNewsMessage.Item> items = new ArrayList<>();
        for (Article article : articles) {
            WxXmlOutNewsMessage.Item item = new WxXmlOutNewsMessage.Item();
            item.setDescription(article.getDescription());
            item.setPicUrl(article.getPic_url());
            item.setTitle(article.getTitle());
            item.setUrl(article.getUrl());
            items.add(item);
        }
        menuconfig.setArticles(items);
    }

    public MenuConfig getMenuConfig(String menukey) {
        cache();
        return menuConfigMap.get(menukey);
    }

    public MenuConfig getProConfig(String pro_name) {
        MenuConfig config = new MenuConfig();
        config.setArticle_id(pro_name);
        addArticles(config);
        return config;
    }


    public void save(MenuConfig menuConfig) {
        try {
            System.out.println(dao.saveMenuConfig(menuConfig));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(Article article) {
        try {
            System.out.println(dao.saveArticle(article));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void cache() {
//        if(menuConfigMap.size()!=0){
//            return;
//        }
        menuConfigMap.clear();
        getMenuConfigs();
    }

}
