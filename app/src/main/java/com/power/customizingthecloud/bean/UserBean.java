package com.power.customizingthecloud.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/14.
 */

public class UserBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"id":13,"user_name":"","user_eselsohr":0,"user_balance":"0.00","user_avatar":"","voucher_count":0,"package_count":0}
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
         * id : 13
         * user_name :
         * user_eselsohr : 0
         * user_balance : 0.00
         * user_avatar :
         * voucher_count : 0
         * package_count : 0
         */

        private int id;
        private String user_name;
        private int user_eselsohr;
        private String user_balance;
        private String user_avatar;
        private int voucher_count;
        private int package_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public int getUser_eselsohr() {
            return user_eselsohr;
        }

        public void setUser_eselsohr(int user_eselsohr) {
            this.user_eselsohr = user_eselsohr;
        }

        public String getUser_balance() {
            return user_balance;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }

        public String getUser_avatar() {
            return user_avatar;
        }

        public void setUser_avatar(String user_avatar) {
            this.user_avatar = user_avatar;
        }

        public int getVoucher_count() {
            return voucher_count;
        }

        public void setVoucher_count(int voucher_count) {
            this.voucher_count = voucher_count;
        }

        public int getPackage_count() {
            return package_count;
        }

        public void setPackage_count(int package_count) {
            this.package_count = package_count;
        }
    }
}
