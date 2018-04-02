package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class MeatBean {


    /**
     * code : 1
     * data : {"good_sild":[{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}],"goods":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","price":"150.00","name":"驴肉后腿部","id":4}],"position_class":[{"name":"肩胛部","id":1,"sort":0},{"name":"背脊部","id":2,"sort":0},{"name":"肋部","id":3,"sort":0},{"name":"腹部","id":4,"sort":0},{"name":"前腿部","id":5,"sort":0},{"name":"后腿部","id":6,"sort":0}]}
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
         * good_sild : [{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}]
         * goods : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","price":"150.00","name":"驴肉后腿部","id":4}]
         * position_class : [{"name":"肩胛部","id":1,"sort":0},{"name":"背脊部","id":2,"sort":0},{"name":"肋部","id":3,"sort":0},{"name":"腹部","id":4,"sort":0},{"name":"前腿部","id":5,"sort":0},{"name":"后腿部","id":6,"sort":0}]
         */
        private List<GoodSildEntity> good_sild;
        private List<GoodsEntity> goods;
        private List<PositionClassEntity> position_class;

        public void setGood_sild(List<GoodSildEntity> good_sild) {
            this.good_sild = good_sild;
        }

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public void setPosition_class(List<PositionClassEntity> position_class) {
            this.position_class = position_class;
        }

        public List<GoodSildEntity> getGood_sild() {
            return good_sild;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public List<PositionClassEntity> getPosition_class() {
            return position_class;
        }

        public static class GoodSildEntity {
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
             * unit : 斤
             * price : 150.00
             * name : 驴肉后腿部
             * id : 4
             */
            private String image;
            private String unit;
            private String price;
            private String name;
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

            public int getId() {
                return id;
            }
        }

        public static class PositionClassEntity {
            /**
             * name : 肩胛部
             * id : 1
             * sort : 0
             */
            private String name;
            private int id;
            private int sort;

            public void setName(String name) {
                this.name = name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getName() {
                return name;
            }

            public int getId() {
                return id;
            }

            public int getSort() {
                return sort;
            }
        }
    }
}
