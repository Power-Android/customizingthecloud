package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/27.
 */
public class ReturnMoneyDetailBean {

    /**
     * code : 1
     * data : {"reason_info":"1233","refund_amount":"150.00","created_at":"2018-07-25 18:52:31","goods":[{"goods_name":"特色有机奶粉","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"150.00","goods_id":3,"goods_num":1,"order_id":163}],"order_id":163,"refund_sn":"919263180725185231"}
     * message : ok
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
         * reason_info : 1233
         * refund_amount : 150.00
         * created_at : 2018-07-25 18:52:31
         * goods : [{"goods_name":"特色有机奶粉","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"150.00","goods_id":3,"goods_num":1,"order_id":163}]
         * order_id : 163
         * refund_sn : 919263180725185231
         */
        private String reason_info;
        private String refund_amount;
        private String created_at;
        private List<GoodsEntity> goods;
        private int order_id;
        private String refund_sn;

        public void setReason_info(String reason_info) {
            this.reason_info = reason_info;
        }

        public void setRefund_amount(String refund_amount) {
            this.refund_amount = refund_amount;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public void setRefund_sn(String refund_sn) {
            this.refund_sn = refund_sn;
        }

        public String getReason_info() {
            return reason_info;
        }

        public String getRefund_amount() {
            return refund_amount;
        }

        public String getCreated_at() {
            return created_at;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public int getOrder_id() {
            return order_id;
        }

        public String getRefund_sn() {
            return refund_sn;
        }

        public static class GoodsEntity {
            /**
             * goods_name : 特色有机奶粉
             * goods_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * goods_price : 150.00
             * goods_id : 3
             * goods_num : 1
             * order_id : 163
             */
            private String goods_name;
            private String goods_image;
            private String goods_price;
            private String class_name;
            private int goods_id;
            private int goods_num;
            private int order_id;

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public void setGoods_name(String goods_name) {
                this.goods_name = goods_name;
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
