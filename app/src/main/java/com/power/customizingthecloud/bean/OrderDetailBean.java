package com.power.customizingthecloud.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */

public class OrderDetailBean {

    /**
     * code : 1
     * data : {"address":"北京市 海淀区 西二旗 中关村软件园","shipping_code":"","reciver_name":"POWER","goods_amount":"150.00","mobile":"15366666666","created_at":"2018-07-06 10:44:09","goods":[{"goods_name":"特色有机奶粉","goods_class":"休闲食品","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"150.00","goods_id":3,"goods_num":1,"order_id":92}],"order_amount":"150.00","order_message":"","id":92,"order_state":40,"shipping_time":"工作日","order_sn":1000000000009301}
     * message : 请求成功
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
         * address : 北京市 海淀区 西二旗 中关村软件园
         * shipping_code :
         * reciver_name : POWER
         * goods_amount : 150.00
         * mobile : 15366666666
         * created_at : 2018-07-06 10:44:09
         * goods : [{"goods_name":"特色有机奶粉","goods_class":"休闲食品","goods_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","goods_price":"150.00","goods_id":3,"goods_num":1,"order_id":92}]
         * order_amount : 150.00
         * order_message :
         * id : 92
         * order_state : 40
         * shipping_time : 工作日
         * order_sn : 1000000000009301
         */
        private String address;
        private String shipping_code;
        private String reciver_name;
        private String goods_amount;
        private String mobile;
        private String created_at;
        private List<GoodsEntity> goods;
        private String order_amount;
        private String order_message;
        private int id;
        private int order_state;
        private String shipping_time;
        private long order_sn;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setShipping_code(String shipping_code) {
            this.shipping_code = shipping_code;
        }

        public void setReciver_name(String reciver_name) {
            this.reciver_name = reciver_name;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public void setOrder_message(String order_message) {
            this.order_message = order_message;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setOrder_state(int order_state) {
            this.order_state = order_state;
        }

        public void setShipping_time(String shipping_time) {
            this.shipping_time = shipping_time;
        }

        public void setOrder_sn(long order_sn) {
            this.order_sn = order_sn;
        }

        public String getAddress() {
            return address;
        }

        public String getShipping_code() {
            return shipping_code;
        }

        public String getReciver_name() {
            return reciver_name;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public String getMobile() {
            return mobile;
        }

        public String getCreated_at() {
            return created_at;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public String getOrder_message() {
            return order_message;
        }

        public int getId() {
            return id;
        }

        public int getOrder_state() {
            return order_state;
        }

        public String getShipping_time() {
            return shipping_time;
        }

        public long getOrder_sn() {
            return order_sn;
        }

        public static class GoodsEntity {
            /**
             * goods_name : 特色有机奶粉
             * goods_class : 休闲食品
             * goods_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * goods_price : 150.00
             * goods_id : 3
             * goods_num : 1
             * order_id : 92
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
