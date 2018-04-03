package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class ShengXianBean {

    /**
     * code : 1
     * data : {"goods":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","price":"150.00","name":"驴肉后腿部","good_type":"good","id":4}],"position_class":[{"name":"肩胛部","id":1,"sort":0},{"name":"背脊部","id":2,"sort":0},{"name":"肋部","id":3,"sort":0},{"name":"腹部","id":4,"sort":0},{"name":"前腿部","id":5,"sort":0},{"name":"后腿部","id":6,"sort":0}]}
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
         * goods : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","price":"150.00","name":"驴肉后腿部","good_type":"good","id":4}]
         * position_class : [{"name":"肩胛部","id":1,"sort":0},{"name":"背脊部","id":2,"sort":0},{"name":"肋部","id":3,"sort":0},{"name":"腹部","id":4,"sort":0},{"name":"前腿部","id":5,"sort":0},{"name":"后腿部","id":6,"sort":0}]
         */
        private List<GoodsEntity> goods;
        private List<PositionClassEntity> position_class;

        public void setGoods(List<GoodsEntity> goods) {
            this.goods = goods;
        }

        public void setPosition_class(List<PositionClassEntity> position_class) {
            this.position_class = position_class;
        }

        public List<GoodsEntity> getGoods() {
            return goods;
        }

        public List<PositionClassEntity> getPosition_class() {
            return position_class;
        }

        public static class GoodsEntity {
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
