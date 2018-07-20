package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/20.
 */

public class BindEmailBean {

    /**
     * code : 1
     * data : {"user_email":"634381967@qq.com"}
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
         * user_email : 634381967@qq.com
         */
        private String user_email;

        public void setUser_email(String user_email) {
            this.user_email = user_email;
        }

        public String getUser_email() {
            return user_email;
        }
    }
}
