package com.example.admin.utils.share;

import android.graphics.Bitmap;

/**
 * function：分享Bean类
 * author by admin
 * create on 2018/4/28.
 */
public class ShareBean {

    private String title;
    private String content;
    private String targetUrl;
    private String imgUrl;
    private byte[] imageByte;
    private Bitmap shareBitmap;

    public static ShareBean createShareBean(String title, String content, String targetUrl, String imgUrl, Bitmap shareBitmap) {
        ShareBean shareBean = new ShareBean();
        shareBean.setTitle(title);
        shareBean.setContent(content);
        shareBean.setTargetUrl(targetUrl);
        shareBean.setImgUrl(imgUrl);
        shareBean.setShareBitmap(shareBitmap);
        return shareBean;
    }


    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Bitmap getShareBitmap() {
        return shareBitmap;
    }

    public void setShareBitmap(Bitmap shareBitmap) {
        this.shareBitmap = shareBitmap;
    }
}
