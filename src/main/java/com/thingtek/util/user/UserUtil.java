package com.thingtek.util.user;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.bean.result.WxUserListResult;
import com.soecode.wxtools.bean.result.WxUserTagResult;
import com.soecode.wxtools.exception.WxErrorException;

import java.util.ArrayList;
import java.util.List;

public class UserUtil {

    /* ---创建用户标签--- */
    public void createUserTag(IService iService) {
        try {
            WxUserTagResult result = iService.createUserTag(
                    "组名");
            System.out.println(result.getTag().getId());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---删除用户标签--- */
    public void deleteUserTag(IService iService) {
        try {
            iService.deleteUserTag(2);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---查询所有用户标签--- */
    public void queryAllUserTag(IService iService) {
        try {
            WxUserTagResult result = iService.queryAllUserTag();
            System.out.println(result);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    /* ---更新用户标签名字--- */
    public void updateUserTagName(IService iService) {
        try {
            //标签ID，新标签名
            iService.updateUserTagName(1, "new tag name");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---移动用户在某标签--- */
    /* ---批量移动用户在某标签--- */
    public void batchMovingUserToNewTag(IService iService) {
        List<String> openidList = new ArrayList<>();
        openidList.add("openid1");
        openidList.add("openid2");
        try {
            iService.batchMovingUserToNewTag(openidList, 2);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---批量移除用户在某标签--- */
    public void batchRemoveUserTag(IService iService) {
        List<String> openidList = new ArrayList<>();
        openidList.add("openid1");
        openidList.add("openid2");
        try {
            //用户Openid列表, tagID
            iService.batchRemoveUserTag(openidList, 2);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


    /* ---修改用户备注--- */
    public void updateUserRemark(IService iService) {
        try {
            iService.updateUserRemark("openid", "备注名");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---获取用户基本信息--- */
    public void getUserInfoByOpenId(IService iService) {
        try {
            WxUserList.WxUser user = iService.
                    getUserInfoByOpenId(
                            new WxUserList.WxUser.WxUserGet("openid", WxConsts.LANG_CHINA));
            System.out.println(user.toString());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---批量获取用户信息--- */
    public void batchGetUserInfo(IService iService) {
        List<WxUserList.WxUser.WxUserGet> list = new ArrayList<>();
        WxUserList.WxUser.WxUserGet userGet1 =
                new WxUserList.WxUser.WxUserGet("openid", WxConsts.LANG_CHINA);
        WxUserList.WxUser.WxUserGet userGet2 =
                new WxUserList.WxUser.WxUserGet("openid", WxConsts.LANG_CHINA);
        list.add(userGet1);
        list.add(userGet2);
        try {
            WxUserList userList = iService.batchGetUserInfo(list
            );
            System.out.println(userList.toString());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---批量获取关注者openid--- */
    public void batchGetUserOpenId(IService iService) {
        try {
            //第一个openid之后拉取
            WxUserListResult result = iService.batchGetUserOpenId("next openid");
            System.out.println(result.getNext_openid());
            System.out.println(result.getData());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }

    /* ---认证获取用户信息--第一步：构造URL获取Code--- */
    public void oauth2buildAuthorizationUrl(IService iService) {
        try {
            String oauthUrl = iService.oauth2buildAuthorizationUrl(
                    "回调URL", WxConsts.OAUTH2_SCOPE_USER_INFO, "自定义携带参数");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        /* "回调URL"是指当用户点击授权,允许获取用户信息之后,页面跳转的地方.
          一般填写Controller或者Servlet等可以处理code的地方. */
        //"自定义携带参数": stage, 微信允许开发者带自定义参数去回调URL上.

    }

    /* ---认证获取用户信息--第二步：拿code换token和openid--- */
    public void oauth2ToGetAccessToken(IService iService) {
        try {
            WxOAuth2AccessTokenResult result = iService.oauth2ToGetAccessToken("code");
            System.out.println(result.getAccess_token());
            System.out.println(result.getOpenid());
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

    }

    /* ---认证获取用户信息--第三步：拿token和openid换用户信息--- */
    public void oauth2ToGetUserInfo(IService iService) {
        try {
            WxUserList.WxUser user = iService.oauth2ToGetUserInfo("token",
                    new WxUserList.WxUser.WxUserGet("openid", WxConsts.LANG_CHINA));
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }


}
