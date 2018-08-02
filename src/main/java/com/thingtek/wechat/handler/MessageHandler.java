package com.thingtek.wechat.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.WxXmlOutNewsMessage;
import com.soecode.wxtools.bean.outxmlbuilder.NewsBuilder;
import com.soecode.wxtools.exception.WxErrorException;
import com.thingtek.user.entity.UserBean;
import com.thingtek.user.service.UserService;
import com.thingtek.wechat.menu.entity.MenuConfig;
import com.thingtek.wechat.menu.service.MenuConfigService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@Component
public class MessageHandler implements WxMessageHandler {


    private static final int FILE = 0;
    private static final int MEDIAID = 1;
    private static final int OTHER = -1;

    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxMessage, Map<String, Object> context, IService iService) {
        try {
            wxService = (WxService) iService;
            switch (wxMessage.getMsgType()) {
                case WxConsts.XML_MSG_EVENT:
                    switch (wxMessage.getEvent()) {
                        case WxConsts.EVT_CLICK:
                        case WxConsts.MENU_BUTTON_CLICK:
                            return clickMenuEvent(wxMessage);
                        case WxConsts.EVT_SCAN:
                        case WxConsts.EVT_SUBSCRIBE:
                            return scanORsubscribe(wxMessage);
                    }
                    break;
                case WxConsts.XML_MSG_TEXT:
                    break;
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Resource
    private UserService userService;

    private WxXmlOutMessage scanORsubscribe(WxXmlMessage wxMessage) throws WxErrorException {

        switch (wxMessage.getEvent()) {
            case WxConsts.EVT_SUBSCRIBE:
                /* -----------------关注消息------------------- */
                return getTextXmlOutMessage(wxMessage, "您好!");
            case WxConsts.EVT_SCAN:
                UserBean user = userService.findSubUserByUserId(wxMessage.getFromUserName());
                if (user == null) {
                    user = new UserBean();
                    user.setUser_id(wxMessage.getFromUserName());
                    user.setFollow_state(true);
                    userService.follow(user);
                    break;
                }
                /* ------------------主项目消息------------------ */
                return user.getPro_name() != null ? getNewsXmlOutMessage(wxMessage, menuConfigService.getProConfig(user.getPro_name()).getArticles()) : getTextXmlOutMessage(wxMessage, "欢迎回来!");
        }
        return null;
    }


    @Resource
    private MenuConfigService menuConfigService;

    private WxXmlOutMessage clickMenuEvent(WxXmlMessage wxMessage) throws WxErrorException {
        MenuConfig config = menuConfigService.getMenuConfig(wxMessage.getEventKey());
        System.err.println("config:" + config);
        if (config == null) {
            return null;
        }
        switch (config.getMessage_type()) {
            case WxConsts.XML_MSG_TEXT:
                return WxXmlOutMessage.TEXT().content(config.getContent()).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
            case WxConsts.XML_MSG_IMAGE:
                /*if (config.getMedia_id() != null)
                    return getMediaXmlOutMessage(wxMessage, config.getMessage_type(), config.getMedia_id(), MEDIAID);
                else if (config.getFile_url() != null)
                    return getMediaXmlOutMessage(wxMessage, config.getMessage_type(), config.getFile_url(), FILE);
                else return getMediaXmlOutMessage(wxMessage, "消息不见了!");*/
                return getMediaXmlOutMessage(wxMessage, config.getMessage_type(), config.getMedia_id() != null ? config.getMedia_id() : config.getFile_url(), config.getMedia_id() != null ? MEDIAID : config.getFile_url() != null ? FILE : OTHER);
            case WxConsts.XML_MSG_NEWS:
                return getNewsXmlOutMessage(wxMessage, config.getArticles());
            case WxConsts.XML_MSG_LINK:
                break;
            case WxConsts.XML_MSG_LOCATION:
                break;
            case WxConsts.XML_MSG_MUSIC:
                break;
            case WxConsts.XML_MSG_VIDEO:
                break;
            case WxConsts.XML_MSG_VOICE:
                return WxXmlOutMessage.VOICE().mediaId(config.getMedia_id()).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
        }
        return null;
    }

    private WxService wxService;

    private WxXmlOutMessage getTextXmlOutMessage(WxXmlMessage wxMessage, String content) {
        return WxXmlOutMessage.TEXT().content(content).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
    }


    private WxXmlOutMessage getNewsXmlOutMessage(WxXmlMessage wxMessage, List<WxXmlOutNewsMessage.Item> items) {
        NewsBuilder news = WxXmlOutMessage.NEWS().toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName());
        for (WxXmlOutNewsMessage.Item item : items) news.addArticle(item);
        return news.build();
    }


    private WxXmlOutMessage getMediaXmlOutMessage(WxXmlMessage wxMessage, String mediaType, String path, int type) throws WxErrorException {
        switch (type) {
            case FILE://使用文件路径
                String mediaId = uploadMedia(mediaType, path);
                return getMediaXmlOutMessage(wxMessage, mediaType, mediaId, mediaId != null ? MEDIAID : OTHER);
            case MEDIAID://使用mediaid
                return WxXmlOutMessage.IMAGE().mediaId(path).toUser(wxMessage.getFromUserName()).fromUser(wxMessage.getToUserName()).build();
            default: //没有文件路径和mediaid
                return getTextXmlOutMessage(wxMessage, path != null ? path : "消息不见了");
        }
    }

    /*
            上传临时素材
     */
    private String uploadMedia(String type, String path) throws WxErrorException {
        switch (type) {
            case WxConsts.MEDIA_IMAGE:
                File file = getFileByPath(path);
                return file != null ? wxService.uploadTempMedia(WxConsts.MEDIA_IMAGE, file).getMedia_id() : null;
        }
        return null;
    }

    private File getFileByPath(String path) {
        try {
            return new File(this.getClass().getClassLoader().getResource(path).toURI().getPath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

}
