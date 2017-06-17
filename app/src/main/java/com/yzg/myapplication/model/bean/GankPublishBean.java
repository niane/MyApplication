package com.yzg.myapplication.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yzg on 2017/5/2.
 */

public class GankPublishBean implements Serializable{

    /**
     * _id : 590568e5421aa90c7a8b2a98
     * createdAt : 2017-04-30T12:32:37.940Z
     * desc : 可进行 UI 定制的日历组件，轻松完成签到日历功能。
     * images : ["http://img.gank.io/ac4982fe-05a2-4704-ad12-11bdfc074769"]
     * publishedAt : 2017-05-02T12:00:17.802Z
     * source : web
     * type : Android
     * url : https://github.com/shichaohui/EasyCalendar
     * used : true
     * who : 石朝辉
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
