package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class MiaoListBean {

    /**
     * code : 1
     * data : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","seckill_end_time":1529375559,"price":"150.00","name":"特色有机奶粉","good_type":"seckill","seckill_price":"100.00","id":3,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"150.00","name":"驴肉后腿部","good_type":"seckill","seckill_price":"100.00","id":4,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":20}]
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
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * unit : 罐
         * seckill_end_time : 1529375559
         * price : 150.00
         * name : 特色有机奶粉
         * good_type : seckill
         * seckill_price : 100.00
         * id : 3
         * seckill_note :
         * seckill_start_time : 1516621657
         * seckill_storage : 30
         */
        private String image;
        private String unit;
        private int seckill_end_time;
        private String price;
        private String name;
        private String good_type;
        private String seckill_price;
        private int id;
        private String seckill_note;
        private int seckill_start_time;
        private int seckill_storage;

        public void setImage(String image) {
            this.image = image;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setSeckill_end_time(int seckill_end_time) {
            this.seckill_end_time = seckill_end_time;
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

        public void setSeckill_price(String seckill_price) {
            this.seckill_price = seckill_price;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setSeckill_note(String seckill_note) {
            this.seckill_note = seckill_note;
        }

        public void setSeckill_start_time(int seckill_start_time) {
            this.seckill_start_time = seckill_start_time;
        }

        public void setSeckill_storage(int seckill_storage) {
            this.seckill_storage = seckill_storage;
        }

        public String getImage() {
            return image;
        }

        public String getUnit() {
            return unit;
        }

        public int getSeckill_end_time() {
            return seckill_end_time;
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

        public String getSeckill_price() {
            return seckill_price;
        }

        public int getId() {
            return id;
        }

        public String getSeckill_note() {
            return seckill_note;
        }

        public int getSeckill_start_time() {
            return seckill_start_time;
        }

        public int getSeckill_storage() {
            return seckill_storage;
        }
    }
}
