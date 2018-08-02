package com.thingtek.util.media;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxVideoIntroduction;
import com.soecode.wxtools.bean.result.*;
import com.soecode.wxtools.exception.WxErrorException;

import java.io.File;

public class MediaUtil {


    /* ---上传临时文件到微信服务器--- */
    public void uploadTempMedia(IService iService) throws WxErrorException {
        //可以上传file或者InputSteam，拿到MediaID
        WxMediaUploadResult result =
                iService.uploadTempMedia(WxConsts.MEDIA_IMAGE, new File("E://test.jpg"));
        System.out.println(result.getMedia_id());
    }

    /* ---downloadTempMedia 下载临时文件--- */
    public void downloadTempMedia(IService iService) throws WxErrorException {
        //可以上传file或者InputSteam，拿到MediaID
        WxMediaUploadResult result =
                iService.uploadTempMedia(WxConsts.MEDIA_IMAGE, new File("E://test.jpg"));
        System.out.println(result.getMedia_id());
    }

    /* ---uploadMedia 上传永久文件到微信服务器--- */
    public void uploadMedia(IService iService) throws WxErrorException {
        //这里注意，如果是上传非视频格式的素材，第三个参数(WxVideoIntroduction)为null即可
        WxMediaUploadResult result1 = iService.uploadMedia(WxConsts.MEDIA_VOICE, new File("E://test.m4a"), null);
        //如果是上传视频Video，可以添加描述
        WxVideoIntroduction intro = new WxVideoIntroduction();
        intro.setTitle("视频1");
        intro.setIntroduction("描述1");
        WxMediaUploadResult result2 = iService.uploadMedia(WxConsts.MEDIA_VIDEO, new File("E://test.mp4"), intro);
    }

    /* ---downloadMedia 下载永久文件--- */
    public void downloadMedia(IService iService) throws WxErrorException {
        File file = iService.downloadMedia("media_id", new File("E://temp"));
    }

    /* ---downloadNewsMedia 下载图文素材--- */
    public void downloadNewsMedia(IService iService) throws WxErrorException {
        //图文结果
        WxNewsMediaResult result = iService.downloadNewsMedia("media_id");
        System.out.println(result.toString());
    }

    /* ---downloadVideoMedia 下载视频素材--- */
    public void downloadVideoMedia(IService iService) throws WxErrorException {
        //视频结果，取出URL即可下载
        WxVideoMediaResult result = iService.downloadVideoMedia("media_id", new File("E://temp"));
        System.out.println(result.toString());
    }

    /* ---deleteMediaMaterial 删除素材资源--- */
    public void deleteMediaMaterial(IService iService) throws WxErrorException {
        WxError result = iService.deleteMediaMaterial("media_id");
        System.out.println(result.getErrcode());
    }

    /* ---imageDomainChange 上传图片变成腾讯域名下的图片--- */
    public void imageDomainChange(IService iService) throws WxErrorException {
        WxMediaUploadResult result = iService.imageDomainChange(new File("E://test.jpg"));
        System.out.println(result.getUrl());
    }

    /* ---getMaterialCount 获取永久素材数量接口--- */
    public void getMaterialCount(IService iService) throws WxErrorException {
        WxMaterialCountResult result = iService.getMaterialCount();
        System.out.println(result.getImage_count());
        System.out.println(result.getNews_count());
    }

    /* ---batchGetMeterial 批量获取永久素材资源信息--- */
    public void batchGetMeterial(IService iService) throws WxErrorException {
        WxBatchGetMaterialResult result = iService.batchGetMeterial(WxConsts.MEDIA_IMAGE, 0, 5);
    }
}
