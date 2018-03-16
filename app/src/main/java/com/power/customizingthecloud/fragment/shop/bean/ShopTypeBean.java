package com.power.customizingthecloud.fragment.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ShopTypeBean {

    /**
     * code : 1
     * data : {"series_class":[{"name":"生鲜系列","id":1,"sort":0},{"name":"养生系列","id":2,"sort":0},{"name":"休闲食品","id":3,"sort":0},{"name":"大单预定","id":4,"sort":0},{"name":"VIP私人订制专享","id":5,"sort":0},{"name":"成为会员","id":6,"sort":0},{"name":"领卷区","id":7,"sort":0}],"good_class":[{"name":"生鲜肉类","id":1,"sort":0},{"name":"品牌精选","id":2,"sort":0},{"name":"地方特产","id":3,"sort":0},{"name":"养生保健","id":4,"sort":0}]}
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
         * series_class : [{"name":"生鲜系列","id":1,"sort":0},{"name":"养生系列","id":2,"sort":0},{"name":"休闲食品","id":3,"sort":0},{"name":"大单预定","id":4,"sort":0},{"name":"VIP私人订制专享","id":5,"sort":0},{"name":"成为会员","id":6,"sort":0},{"name":"领卷区","id":7,"sort":0}]
         * good_class : [{"name":"生鲜肉类","id":1,"sort":0},{"name":"品牌精选","id":2,"sort":0},{"name":"地方特产","id":3,"sort":0},{"name":"养生保健","id":4,"sort":0}]
         */
        private List<SeriesClassEntity> series_class;
        private List<GoodClassEntity> good_class;

        public void setSeries_class(List<SeriesClassEntity> series_class) {
            this.series_class = series_class;
        }

        public void setGood_class(List<GoodClassEntity> good_class) {
            this.good_class = good_class;
        }

        public List<SeriesClassEntity> getSeries_class() {
            return series_class;
        }

        public List<GoodClassEntity> getGood_class() {
            return good_class;
        }

        public static class SeriesClassEntity implements Serializable{
            /**
             * name : 生鲜系列
             * id : 1
             * sort : 0
             */
            private String name;
            private int id;
            private int sort;
            private boolean isChecked;

            public boolean isChecked() {
                return isChecked;
            }

            public void setChecked(boolean checked) {
                isChecked = checked;
            }

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

        public static class GoodClassEntity {
            /**
             * name : 生鲜肉类
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
