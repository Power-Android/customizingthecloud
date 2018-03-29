package com.power.customizingthecloud.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/25.
 */

public class MyOderBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : [{"id":5,"order_sn":"1000000000000501","goods":[{"0":"","order_id":5,"goods_id":1,"goods_name":"测试礼包","goods_price":"100.00","goods_num":1,"goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png"}]},{"id":3,"order_sn":"1000000000000301","goods":[{"0":"","order_id":3,"goods_id":1,"goods_name":"有机奶粉","goods_price":"100.00","goods_num":1,"goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png"}]},{"id":2,"order_sn":"1000000000000201","goods":[{"0":"","order_id":2,"goods_id":1,"goods_name":"有机奶粉","goods_price":"100.00","goods_num":1,"goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png"}]},{"id":1,"order_sn":"1000000000000101","goods":[{"0":"","order_id":1,"goods_id":1,"goods_name":"有机奶粉","goods_price":"100.00","goods_num":2,"goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png"}]}]
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
         * id : 5
         * order_sn : 1000000000000501
         * goods : [{"0":"","order_id":5,"goods_id":1,"goods_name":"测试礼包","goods_price":"100.00","goods_num":1,"goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png"}]
         */

        private int id;
        private String order_sn;
        private String order_state;
        private List<GoodsBean> goods;

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public List<GoodsBean> getGoods() {
            return goods;
        }

        public void setGoods(List<GoodsBean> goods) {
            this.goods = goods;
        }

        public static class GoodsBean {
            /**
             * 0 :
             * order_id : 5
             * goods_id : 1
             * goods_name : 测试礼包
             * goods_price : 100.00
             * goods_num : 1
             * goods_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             */
            private int order_id;
            private int goods_id;
            private String goods_name;
            private String goods_price;
            private int goods_num;
            private String goods_image;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }
        }
    }
}
