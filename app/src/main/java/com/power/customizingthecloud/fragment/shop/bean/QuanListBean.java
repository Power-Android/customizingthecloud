package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class QuanListBean {

    /**
     * code : 1
     * data : [{"end_date":"2018-09-19","price":20,"id":3,"state":1,"title":"奶粉代金券2","order_limit":40,"start_date":"2018-01-23"},{"end_date":"2018-09-19","price":30,"id":2,"state":1,"title":"奶粉代金券1","order_limit":50,"start_date":"2018-01-23"},{"end_date":"2018-09-19","price":10,"id":1,"state":1,"title":"奶粉代金券","order_limit":30,"start_date":"2018-01-23"}]
     * message : 请求成功
     */
    private int code;
    private List<DataEntity> data;
    private String message;

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static class DataEntity {
        /**
         * end_date : 2018-09-19
         * price : 20
         * id : 3
         * state : 1
         * title : 奶粉代金券2
         * order_limit : 40
         * start_date : 2018-01-23
         */
        private String end_date;
        private int price;
        private int id;
        private int state;
        private String title;
        private int order_limit;
        private String start_date;

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setOrder_limit(int order_limit) {
            this.order_limit = order_limit;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public int getPrice() {
            return price;
        }

        public int getId() {
            return id;
        }

        public int getState() {
            return state;
        }

        public String getTitle() {
            return title;
        }

        public int getOrder_limit() {
            return order_limit;
        }

        public String getStart_date() {
            return start_date;
        }
    }
}
