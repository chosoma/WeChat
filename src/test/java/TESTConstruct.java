import com.thingtek.project_view.entity.WarnBean;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.Date;

public class TESTConstruct {

    @Test
    public void testConstruce() {
        WarnBean warn = new WarnBean();
        warn.setTime(new Date());
        try {

            Class clazz = this.getClass()
                    .getClassLoader()
                    .loadClass("com.thingtek.wechat.templates.Template");
            Constructor constructor = clazz.getConstructor(WarnBean.class);

            System.out.println(constructor.newInstance(warn));

        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}
