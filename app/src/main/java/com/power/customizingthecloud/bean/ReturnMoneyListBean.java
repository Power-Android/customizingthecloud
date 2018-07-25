package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/25.
 */

public class ReturnMoneyListBean {

    /**
     * code : 1
     * data : [{"goods":[{"goods_name":"驴肉后腿部","goods_class":"生鲜系列","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"150.00","goods_id":4,"goods_num":1,"order_id":137}],"id":137,"order_sn":1000000000013901}]
     * message : ok
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
         * goods : [{"goods_name":"驴肉后腿部","goods_class":"生鲜系列","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"150.00","goods_id":4,"goods_num":1,"order_id":137}]
         * id : 137
         * order_sn : 1000000000013901
         */
        private List<GoodsEntity> goods;
        private int id;
        private long order_sn;

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setOrder_sn(long order_sn) {
            this.order_sn = order_sn;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public int getId() {
            return id;
        }

        public long getOrder_sn() {
            return order_sn;
        }

        public static class GoodsEntity {
            /**
             * goods_name : 驴肉后腿部
             * goods_class : 生鲜系列
             * goods_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * goods_price : 150.00
             * goods_id : 4
             * goods_num : 1
             * order_id : 137
             */
            private String goods_name;
            private String goods_class;
            private String goods_image;
            private String goods_price;
            private int goods_id;
            private int goods_num;
            private int order_id;

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
            }

            public void setGoods_class(String goods_class) {
                this.goods_class = goods_class;
            }

            public void setGoods_image(String goods_image) {
                this.goods_image = goods_image;
            }

            public void setGoods_price(String goods_price) {
                this.goods_price = goods_price;
            }

            public void setGoods_id(int goods_id) {
                this.goods_id = goods_id;
            }

            public void setGoods_num(int goods_num) {
                this.goods_num = goods_num;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getGoods_name() {
                return goods_name;
            }

            public String getGoods_class() {
                return goods_class;
            }

            public String getGoods_image() {
                return goods_image;
            }

            public String getGoods_price() {
                return goods_price;
            }

            public int getGoods_id() {
                return goods_id;
            }

            public int getGoods_num() {
                return goods_num;
            }

            public int getOrder_id() {
                return order_id;
            }
        }
    }
}
