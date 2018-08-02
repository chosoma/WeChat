import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.result.TemplateListResult;
import com.soecode.wxtools.exception.WxErrorException;
import org.junit.Test;

public class TestTemplate {


    @Test
    public void templateGetList() {
        WxService iService = new WxService();
        TemplateListResult result = null;
        try {
            result = iService.templateGetList();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }

}
