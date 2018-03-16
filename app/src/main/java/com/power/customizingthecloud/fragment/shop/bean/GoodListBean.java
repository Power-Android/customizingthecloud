package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class GoodListBean {

    /**
     * code : 1
     * data : [{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"100.00","name":"有机奶粉","good_type":"good","id":1,"class_name":"品牌精选"},{"gc_class_id":2,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"150.00","name":"特色有机奶粉","good_type":"good","id":3,"class_name":"品牌精选"}]
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
         * gc_class_id : 2
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * unit : 罐
         * price : 100.00
         * name : 有机奶粉
         * good_type : good
         * id : 1
         * class_name : 品牌精选
         */
        private int gc_class_id;
        private String image;
        private String unit;
        private String price;
        private String name;
        private String good_type;
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

        public int getId() {
            return id;
        }

        public String getClass_name() {
            return class_name;
        }
    }
}
