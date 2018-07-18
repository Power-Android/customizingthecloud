package com.power.customizingthecloud.bean;

/**
 * Created by Administrator on 2018/7/16.
 */

public class CaiFuBean {

    /**
     * code : 1
     * data : {"principal":"10000.00","donkey_num":1,"expected":"1479.45","return_income":"0.00","last_income":"0.00"}
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
         * principal : 10000.00
         * donkey_num : 1
         * expected : 1479.45
         * return_income : 0.00
         * last_income : 0.00
         */
        private String principal;
        private int donkey_num;
        private String expected;
        private String return_income;
        private String last_income;
        private String  donkey_profit;

        public String getDonkey_profit() {
            return donkey_profit;
        }

        public void setDonkey_profit(String donkey_profit) {
            this.donkey_profit = donkey_profit;
        }

        public void setPrincipal(String principal) {
            this.principal = principal;
        }

        public void setDonkey_num(int donkey_num) {
            this.donkey_num = donkey_num;
        }

        public void setExpected(String expected) {
            this.expected = expected;
        }

        public void setReturn_income(String return_income) {
            this.return_income = return_income;
        }

        public void setLast_income(String last_income) {
            this.last_income = last_income;
        }

        public String getPrincipal() {
            return principal;
        }

        public int getDonkey_num() {
            return donkey_num;
        }

        public String getExpected() {
            return expected;
        }

        public String getReturn_income() {
            return return_income;
        }

        public String getLast_income() {
            return last_income;
        }
    }
}
