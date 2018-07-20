package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/20.
 */

public class BindAliBean {

    /**
     * code : 1
     * data : {"user_alipay":""}
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
         * user_alipay :
         */
        private String user_alipay;

        public void setUser_alipay(String user_alipay) {
            this.user_alipay = user_alipay;
        }

        public String getUser_alipay() {
            return user_alipay;
        }
    }
}
