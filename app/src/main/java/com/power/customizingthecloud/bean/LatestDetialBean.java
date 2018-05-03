package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/5/2.
 */

public class LatestDetialBean implements Serializable {

    /**
     * code : 1
     * message : 请求成功
     * data : {"id":2,"admin_id":1,"class_id":2,"title":"最新活动尽在塞上云端","thumb":"http://ssyd.oss-cn-beijing.aliyuncs.com/news/201801/20180129104133.png","body":">"}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * admin_id : 1
         * class_id : 2
         * title : 最新活动尽在塞上云端
         * thumb : http://ssyd.oss-cn-beijing.aliyuncs.com/news/201801/20180129104133.png
         * body : >
         */

        private int id;
        private int admin_id;
        private int class_id;
        private String title;
        private String thumb;
        private String body;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdmin_id() {
            return admin_id;
        }

        public void setAdmin_id(int admin_id) {
            this.admin_id = admin_id;
        }

        public int getClass_id() {
            return class_id;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
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

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }
}
