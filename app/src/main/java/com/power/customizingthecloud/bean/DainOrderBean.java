package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/6.
 */

public class DainOrderBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"goods_name":"有机奶粉","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","distribution_price":10,"created_at":"2018-02-24 19:10:59"}]
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
         * goods_name : 有机奶粉
         * goods_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * distribution_price : 10
         * created_at : 2018-02-24 19:10:59
         */

        private String goods_name;
        private String goods_image;
        private int distribution_price;
        private String created_at;

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getGoods_image() {
            return goods_image;
        }

        public void setGoods_image(String goods_image) {
            this.goods_image = goods_image;
        }

        public int getDistribution_price() {
            return distribution_price;
        }

        public void setDistribution_price(int distribution_price) {
            this.distribution_price = distribution_price;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }
    }
}
