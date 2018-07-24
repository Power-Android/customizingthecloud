package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/6.
 */

public class ReturnMoneyTypeBean {
    /**
     * code : 1
     * data : {"reaseon":[{"reason_info":"商品破损、有污渍","id":2},{"reason_info":"不能按时发货","id":1}],"goods":[{"goods_name":"有机奶粉","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"100.00","goods_id":1,"goods_num":2,"order_id":1}],"order_id":1,"order_sn":"1000000000000101"}
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
         * reaseon : [{"reason_info":"商品破损、有污渍","id":2},{"reason_info":"不能按时发货","id":1}]
         * goods : [{"goods_name":"有机奶粉","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"100.00","goods_id":1,"goods_num":2,"order_id":1}]
         * order_id : 1
         * order_sn : 1000000000000101
         */
        private List<ReaseonEntity> reaseon;
        private List<GoodsEntity> goods;
        private int order_id;
        private String order_sn;

        public void setReaseon(List<ReaseonEntity> reaseon) {
            this.reaseon = reaseon;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public List<ReaseonEntity> getReaseon() {
            return reaseon;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public int getOrder_id() {
            return order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public static class ReaseonEntity {
            /**
             * reason_info : 商品破损、有污渍
             * id : 2
             */
            private String reason_info;
            private int id;
            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

            public void setReason_info(String reason_info) {
                this.reason_info = reason_info;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getReason_info() {
                return reason_info;
            }

            public int getId() {
                return id;
            }
        }

        public static class GoodsEntity {
            /**
             * goods_name : 有机奶粉
             * goods_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * goods_price : 100.00
             * goods_id : 1
             * goods_num : 2
             * order_id : 1
             */
            private String goods_name;
            private String goods_image;
            private String goods_price;
            private int goods_id;
            private int goods_num;
            private int order_id;

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
