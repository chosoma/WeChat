import com.thingtek.wechat.menu.dao.MenuConfigDao;
import com.thingtek.wechat.menu.entity.Article;
import com.thingtek.wechat.menu.entity.MenuConfig;
import com.thingtek.wechat.menu.service.MenuConfigService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestMenuConfig {
    private ApplicationContext ac;
    private MenuConfigService service;
    private MenuConfigDao dao;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("classpath:appcontext/applicationContext.xml");
        service = ac.getBean(MenuConfigService.class);
        dao = ac.getBean(MenuConfigDao.class);
    }


    @Test
    public void testMenuConfig() {


        MenuConfig menuConfig = new MenuConfig();
        menuConfig.setMessage_type("image");
        menuConfig.setMenu_key("22");
        try {
            dao.saveMenuConfig(menuConfig);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        service.save(menuConfig);

    }

    @Test
    public void testArticle() {
        Article article = new Article();
        article.setNum(2);
        article.setArticle_id("32");
        article.setDescription("讯泰科技");
        article.setTitle("讯泰科技");
        article.setPic_url("http://1ec88add.ngrok.io/image/InfoLogo.png");
        article.setUrl("http://1ec88add.ngrok.io/pro/join.action");
        try {
            dao.saveArticle(article);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @After
    public void destroy() {

    }

}
