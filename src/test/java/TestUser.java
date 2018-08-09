import com.thingtek.user.dao.UserDao;
import com.thingtek.user.entity.UserBean;
import com.thingtek.user.service.UserService;
import com.thingtek.wechat.menu.dao.MenuConfigDao;
import com.thingtek.wechat.menu.service.MenuConfigService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    private ApplicationContext ac;
    private UserService service;
    private UserDao dao;

    @Before
    public void init() {
        ac = new ClassPathXmlApplicationContext("classpath:appcontext/applicationContext.xml");
        service = ac.getBean(UserService.class);
        dao = ac.getBean(UserDao.class);
    }

    @Test
    public void follow(){
        UserBean userBean = new UserBean();
        userBean.setUser_id("onKatvwFMBT5n2SONq_LI7LoBLto");
        service.follow(userBean);
    }

    @Test
    public void updateBind(){
        UserBean userBean = new UserBean();
        userBean.setUser_id("onKatvwFMBT5n2SONq_LI7LoBLto");
        userBean.setPro_name("gil");
        userBean.setSubjective(false);
        System.out.println(service.updateBind(userBean));
    }


    @Test
    public void bind(){
        UserBean userBean = new UserBean();
        userBean.setUser_id("onKatvwFMBT5n2SONq_LI7LoBLto");
        userBean.setPro_name("test1");
        System.out.println(service.saveBind(userBean));
    }

    @Test
    public void find(){
        UserBean userBean = new UserBean();
        userBean.setUser_id("onKatvwFMBT5n2SONq_LI7LoBLto");
        System.out.println(service.findUserByUserId("onKatvwFMBT5n2SONq_LI7LoBLto"));

    }

}
