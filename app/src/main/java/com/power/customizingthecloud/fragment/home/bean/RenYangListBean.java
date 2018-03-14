package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class RenYangListBean {

    /**
     * code : 1
     * data : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","amount":50,"period":"360天","video_url":"","price":"10000.00","id":4,"state":0,"title":"第四期驴妈妈","profit":"15%","last_amount":50},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","amount":50,"period":"360天","video_url":"","price":"10000.00","id":3,"state":1,"title":"第三期驴妈妈","profit":"15%","last_amount":50},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","amount":50,"period":"360天","video_url":"","price":"10000.00","id":2,"state":2,"title":"第二期驴妈妈","profit":"15%","last_amount":50},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","amount":50,"period":"360天","video_url":"","price":"10000.00","id":1,"state":3,"title":"第一期驴妈妈","profit":"15%","last_amount":45}]
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
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png
         * amount : 50
         * period : 360天
         * video_url :
         * price : 10000.00
         * id : 4
         * state : 0
         * title : 第四期驴妈妈
         * profit : 15%
         * last_amount : 50
         */
        private String image;
        private int amount;
        private String period;
        private String video_url;
        private String price;
        private int id;
        private int state;
        private String title;
        private String profit;
        private int last_amount;

        public void setImage(String image) {
            this.image = image;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setPrice(String price) {
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

        public void setProfit(String profit) {
            this.profit = profit;
        }

        public void setLast_amount(int last_amount) {
            this.last_amount = last_amount;
        }

        public String getImage() {
            return image;
        }

        public int getAmount() {
            return amount;
        }

        public String getPeriod() {
            return period;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getPrice() {
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

        public String getProfit() {
            return profit;
        }

        public int getLast_amount() {
            return last_amount;
        }
    }
}
