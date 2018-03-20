package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by power on 2018/3/20.
 */

public class MyRenyangBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":1,"payment_time":"2018-02-28 17:57:24","end_time":"2019-02-23 17:57:24","income":"7397.26","title":"第一期驴妈妈","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","video_url":"","profit":"15%","price":"10000.00","period":"360天"}]
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
         * id : 1
         * payment_time : 2018-02-28 17:57:24
         * end_time : 2019-02-23 17:57:24
         * income : 7397.26
         * title : 第一期驴妈妈
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png
         * video_url :
         * profit : 15%
         * price : 10000.00
         * period : 360天
         */

        private int id;
        private String payment_time;
        private String end_time;
        private String income;
        private String title;
        private String image;
        private String video_url;
        private String profit;
        private String price;
        private String period;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getIncome() {
            return income;
        }

        public void setIncome(String income) {
            this.income = income;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }
    }
}
