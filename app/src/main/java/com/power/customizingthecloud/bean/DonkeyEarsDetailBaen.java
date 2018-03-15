package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/13.
 */

public class DonkeyEarsDetailBaen implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":4,"user_id":1,"number":1,"note":"签到获得1驴耳朵","time":"2018.02.26"},{"id":3,"user_id":1,"number":10,"note":"购买商品抵扣10驴耳朵[订单号：3]","time":"2018.02.24"},{"id":1,"user_id":1,"number":50,"note":"新用户注册，填写您为邀请人获取50驴耳朵","time":"2018.01.19"}]
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
         * id : 4
         * user_id : 1
         * number : 1
         * note : 签到获得1驴耳朵
         * time : 2018.02.26
         */

        private int id;
        private int user_id;
        private int number;
        private String note;
        private String time;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
