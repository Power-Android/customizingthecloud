package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ShopMiaoBean {

    /**
     * code : 1
     * data : {"good_slid":[{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}],"goods":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","is_seckill":1,"seckill_end_time":1529375559,"price":"150.00","name":"特色有机奶粉","good_type":"seckill","seckill_price":"100.00","id":3,"seckill_storage":30,"seckill_start_time":1516621657},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","is_seckill":1,"seckill_end_time":1529375559,"price":"150.00","name":"驴肉后腿部","good_type":"seckill","seckill_price":"100.00","id":4,"seckill_storage":30,"seckill_start_time":1516621657},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","is_seckill":1,"seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_storage":20,"seckill_start_time":1516621657}]}
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
         * goods : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","is_seckill":1,"seckill_end_time":1529375559,"price":"150.00","name":"特色有机奶粉","good_type":"seckill","seckill_price":"100.00","id":3,"seckill_storage":30,"seckill_start_time":1516621657},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","is_seckill":1,"seckill_end_time":1529375559,"price":"150.00","name":"驴肉后腿部","good_type":"seckill","seckill_price":"100.00","id":4,"seckill_storage":30,"seckill_start_time":1516621657},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","is_seckill":1,"seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_storage":20,"seckill_start_time":1516621657}]
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
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * unit : 罐
             * is_seckill : 1
             * seckill_end_time : 1529375559
             * price : 150.00
             * name : 特色有机奶粉
             * good_type : seckill
             * seckill_price : 100.00
             * id : 3
             * seckill_storage : 30
             * seckill_start_time : 1516621657
             */
            private String image;
            private String unit;
            private int is_seckill;
            private int seckill_end_time;
            private String price;
            private String name;
            private String good_type;
            private String seckill_price;
            private int id;
            private int seckill_storage;
            private int seckill_start_time;

            public void setImage(String image) {
                this.image = image;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setIs_seckill(int is_seckill) {
                this.is_seckill = is_seckill;
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

            public void setSeckill_storage(int seckill_storage) {
                this.seckill_storage = seckill_storage;
            }

            public void setSeckill_start_time(int seckill_start_time) {
                this.seckill_start_time = seckill_start_time;
            }

            public String getImage() {
                return image;
            }

            public String getUnit() {
                return unit;
            }

            public int getIs_seckill() {
                return is_seckill;
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

            public int getSeckill_storage() {
                return seckill_storage;
            }

            public int getSeckill_start_time() {
                return seckill_start_time;
            }
        }
    }
}
