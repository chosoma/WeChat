package com.thingtek.wechat.templates;

import com.thingtek.project_view.entity.WarnBean;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

public @Data
class Template {

    private String first;
    private String keyword1;
    private String keyword2;
    private String remark;

    public Template() {
    }

    public Template(WarnBean warnBean) {

        this.first = "您的" + (warnBean.getPro_name() != null ? warnBean.getPro_name() : "") + "工程发生警报!";

        this.keyword1 = warnBean.getWarn_info();

        this.keyword2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(warnBean.getTime() == null ? new Date() : warnBean.getTime());

        this.remark = "请及时处理!";
    }
}
