package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/19.
 */

public class KefuDetailBean {

    /**
     * code : 1
     * data : {"updated_at":"2018-07-16 14:37:01","class_id":1,"created_at":"2018-07-16 14:36:58","id":1,"title":"如何申请售后","content":"如何申请售后如何申请售后"}
     * message : 请求成功
     */
    private int code;
    private DataEntity data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public DataEntity getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * updated_at : 2018-07-16 14:37:01
         * class_id : 1
         * created_at : 2018-07-16 14:36:58
         * id : 1
         * title : 如何申请售后
         * content : 如何申请售后如何申请售后
         */
        private String updated_at;
        private int class_id;
        private String created_at;
        private int id;
        private String title;
        private String content;

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public void setClass_id(int class_id) {
            this.class_id = class_id;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public int getClass_id() {
            return class_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getContent() {
            return content;
        }
    }
}
