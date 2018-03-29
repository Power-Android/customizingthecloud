package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class MyVoucherBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":1,"title":"奶粉代金券","start_date":"2018-01-23","end_date":"2018-09-19","price":10,"order_limit":30,"state":1}]
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

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * title : 奶粉代金券
         * start_date : 2018-01-23
         * end_date : 2018-09-19
         * price : 10
         * order_limit : 30
         * state : 1
         */

        private int id;
        private String title;
        private String start_date;
        private String end_date;
        private int price;
        private int order_limit;
        private int state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public int getOrder_limit() {
            return order_limit;
        }

        public void setOrder_limit(int order_limit) {
            this.order_limit = order_limit;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }
    }
}
