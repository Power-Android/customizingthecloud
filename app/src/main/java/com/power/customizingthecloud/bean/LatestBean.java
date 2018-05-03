package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/26.
 */

public class LatestBean implements Serializable {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"title":"最新资讯尽在塞上云端","thumb":"http://ssyd.oss-cn-beijing.aliyuncs.com/news/201801/2018012698745632.png"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 最新资讯尽在塞上云端
         * thumb : http://ssyd.oss-cn-beijing.aliyuncs.com/news/201801/2018012698745632.png
         */

        private int id;
        private String title;
        private String thumb;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }
}
