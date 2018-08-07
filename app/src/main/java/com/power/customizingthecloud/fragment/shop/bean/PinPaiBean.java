package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class PinPaiBean {


    /**
     * code : 1
     * data : {"good_slid":[{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}],"goods":[{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"150.00","name":"特色有机奶粉","good_type":"good","goodseries":null,"id":3,"class_name":"品牌精选"},{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"200.00","name":"特色有机奶粉","good_type":"good","goodseries":null,"id":2,"class_name":"品牌精选"},{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"100.00","name":"有机奶粉","good_type":"good","goodseries":null,"id":1,"class_name":"品牌精选"}]}
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
         * good_slid : [{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}]
         * goods : [{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"150.00","name":"特色有机奶粉","good_type":"good","goodseries":null,"id":3,"class_name":"品牌精选"},{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"200.00","name":"特色有机奶粉","good_type":"good","goodseries":null,"id":2,"class_name":"品牌精选"},{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"100.00","name":"有机奶粉","good_type":"good","goodseries":null,"id":1,"class_name":"品牌精选"}]
         */
        private List<GoodSlidEntity> good_slid;
        private List<GoodsEntity> goods;

        public void setGood_slid(List<GoodSlidEntity> good_slid) {
            this.good_slid = good_slid;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public List<GoodSlidEntity> getGood_slid() {
            return good_slid;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public static class GoodSlidEntity {
            /**
             * image_url : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png
             * targe_url : 1
             * id : 2
             * state : 1
             * title : 测试轮播图
             * type : 0
             */
            private String image_url;
            private String targe_url;
            private int id;
            private int state;
            private String title;
            private int type;
            private int targe_type;

            public int getTarge_type() {
                return targe_type;
            }

            public void setTarge_type(int targe_type) {
                this.targe_type = targe_type;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public void setTarge_url(String targe_url) {
                this.targe_url = targe_url;
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

            public void setType(int type) {
                this.type = type;
            }

            public String getImage_url() {
                return image_url;
            }

            public String getTarge_url() {
                return targe_url;
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

            public int getType() {
                return type;
            }
        }

        public static class GoodsEntity {
            /**
             * gc_class_id : 2
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * unit : 罐
             * price : 150.00
             * name : 特色有机奶粉
             * good_type : good
             * goodseries : null
             * id : 3
             * class_name : 品牌精选
             */
            private int gc_class_id;
            private String image;
            private String unit;
            private String price;
            private String name;
            private String good_type;
            private String goodseries;
            private int id;
            private String class_name;

            public void setGc_class_id(int gc_class_id) {
                this.gc_class_id = gc_class_id;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setGood_type(String good_type) {
                this.good_type = good_type;
            }

            public void setGoodseries(String goodseries) {
                this.goodseries = goodseries;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public int getGc_class_id() {
                return gc_class_id;
            }

            public String getImage() {
                return image;
            }

            public String getUnit() {
                return unit;
            }

            public String getPrice() {
                return price;
            }

            public String getName() {
                return name;
            }

            public String getGood_type() {
                return good_type;
            }

            public String getGoodseries() {
                return goodseries;
            }

            public int getId() {
                return id;
            }

            public String getClass_name() {
                return class_name;
            }
        }
    }
}
