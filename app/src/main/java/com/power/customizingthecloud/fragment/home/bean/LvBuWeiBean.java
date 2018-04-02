package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/2.
 */

public class LvBuWeiBean {

    /**
     * code : 1
     * data : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","price":"150.00","name":"驴肉后腿部","good_type":"good","id":4}]
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
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * unit : 斤
         * price : 150.00
         * name : 驴肉后腿部
         * good_type : good
         * id : 4
         */
        private String image;
        private String unit;
        private String price;
        private String name;
        private String good_type;
        private int id;

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
    }
}
