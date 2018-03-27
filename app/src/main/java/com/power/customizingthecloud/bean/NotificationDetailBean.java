package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/3/27.
 */

public class NotificationDetailBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"content":"塞上云端app上线啦"}
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
         * content : 塞上云端app上线啦
         */

        private String content;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
