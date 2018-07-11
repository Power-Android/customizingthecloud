package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/1/18.
 */

public class AliPayBean {


    /**
     * code : 1
     * data : {"alipay":""}
     * message : ok
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
         * alipay :
         */
        private String alipay;

        public void setAlipay(String alipay) {
            this.alipay = alipay;
        }

        public String getAlipay() {
            return alipay;
        }
    }
}
