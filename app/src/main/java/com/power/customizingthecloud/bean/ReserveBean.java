package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class ReserveBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"data":[{"id":1,"res_id":1,"user_name":"网三","mobile":"15250735030","number":3,"seat":"散桌","remarks":"会提前到","state":1,"restaurant_time":"2018-02-24 16:34:05","position":"北京市海淀区西二区中关村软件园","name":"驴肉火锅店北京分店","imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","add_time":"2018-02-24 16:34:05"}],"tel":"0215-456985"}
     */

    private int code;
    private String message;
    private DataBeanX data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * data : [{"id":1,"res_id":1,"user_name":"网三","mobile":"15250735030","number":3,"seat":"散桌","remarks":"会提前到","state":1,"restaurant_time":"2018-02-24 16:34:05","position":"北京市海淀区西二区中关村软件园","name":"驴肉火锅店北京分店","imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","add_time":"2018-02-24 16:34:05"}]
         * tel : 0215-456985
         */

        private String tel;
        private List<DataBean> data;

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean implements Serializable{
            /**
             * id : 1
             * res_id : 1
             * user_name : 网三
             * mobile : 15250735030
             * number : 3
             * seat : 散桌
             * remarks : 会提前到
             * state : 1
             * restaurant_time : 2018-02-24 16:34:05
             * position : 北京市海淀区西二区中关村软件园
             * name : 驴肉火锅店北京分店
             * imgurl : http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png
             * add_time : 2018-02-24 16:34:05
             */

            private int id;
            private int res_id;
            private String user_name;
            private String mobile;
            private int number;
            private String seat;
            private String remarks;
            private int state;
            private String restaurant_time;
            private String position;
            private String name;
            private String imgurl;
            private String add_time;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRes_id() {
                return res_id;
            }

            public void setRes_id(int res_id) {
                this.res_id = res_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getNumber() {
                return number;
            }

            public void setNumber(int number) {
                this.number = number;
            }

            public String getSeat() {
                return seat;
            }

            public void setSeat(String seat) {
                this.seat = seat;
            }

            public String getRemarks() {
                return remarks;
            }

            public void setRemarks(String remarks) {
                this.remarks = remarks;
            }

            public int getState() {
                return state;
            }

            public void setState(int state) {
                this.state = state;
            }

            public String getRestaurant_time() {
                return restaurant_time;
            }

            public void setRestaurant_time(String restaurant_time) {
                this.restaurant_time = restaurant_time;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getImgurl() {
                return imgurl;
            }

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public String getAdd_time() {
                return add_time;
            }

            public void setAdd_time(String add_time) {
                this.add_time = add_time;
            }
        }
    }
}
