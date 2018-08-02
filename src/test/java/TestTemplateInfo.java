import com.thingtek.wechat.menu.dao.MenuConfigDao;
import com.thingtek.wechat.menu.service.MenuConfigService;
import com.thingtek.wechat.templateinfo.dao.TemplateInfoDao;
import com.thingtek.wechat.templateinfo.entity.TemplateInfo;
import com.thingtek.wechat.templateinfo.service.TemplateInfoService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class TestTemplateInfo {

    private ApplicationContext ac;
    private TemplateInfoService service;
    private TemplateInfoDao dao;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("classpath:appcontext/applicationContext.xml");
        service = ac.getBean(TemplateInfoService.class);
        dao = ac.getBean(TemplateInfoDao.class);
    }


    @Test
    public void TestTemplateInfo(){
        System.out.println(service.getTemplateInfo("gil"));
    }

}
