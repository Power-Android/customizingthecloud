package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by power on 2018/5/4.
 */

public class MarketShopBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":6,"name":"养生保健驴肉","price":"120.00","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","is_distribution":1,"distribution_price":10,"good_type":"fenxiao"},{"id":3,"name":"特色有机奶粉","price":"150.00","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","is_distribution":1,"distribution_price":20,"good_type":"fenxiao"}]
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
         * id : 6
         * name : 养生保健驴肉
         * price : 120.00
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * is_distribution : 1
         * distribution_price : 10
         * good_type : fenxiao
         */

        private int id;
        private String name;
        private String price;
        private String image;
        private int is_distribution;
        private int distribution_price;
        private String good_type;

        private String title;
        private String profit;
        private int distribution_eselsohr;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getProfit() {
            return profit;
        }

        public void setProfit(String profit) {
            this.profit = profit;
        }

        public int getDistribution_eselsohr() {
            return distribution_eselsohr;
        }

        public void setDistribution_eselsohr(int distribution_eselsohr) {
            this.distribution_eselsohr = distribution_eselsohr;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getIs_distribution() {
            return is_distribution;
        }

        public void setIs_distribution(int is_distribution) {
            this.is_distribution = is_distribution;
        }

        public int getDistribution_price() {
            return distribution_price;
        }

        public void setDistribution_price(int distribution_price) {
            this.distribution_price = distribution_price;
        }

        public String getGood_type() {
            return good_type;
        }

        public void setGood_type(String good_type) {
            this.good_type = good_type;
        }
    }
}
