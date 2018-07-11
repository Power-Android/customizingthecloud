package com.power.customizingthecloud.fragment.home.bean;

/**
 * Created by Administrator on 2018/3/29.
 */

public class PushOrderBean {

    /**
     * code : 1
     * data : {"pay_sn":"640572785859181001"}
     * message : 订单提交成功
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
         * pay_sn : 640572785859181001
         */
        private String order_sn;

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }
    }
}
