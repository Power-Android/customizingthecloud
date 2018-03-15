package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class CanWeiDetailBean {

    /**
     * code : 1
     * data : {"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","slids_img":["http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png"],"recommend_img":["http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png"],"everyone":99,"seat_number":"30","name":"驴肉火锅店北京分店","information":"正宗驴肉火锅店","telephone":"010-33256589","id":1,"position":"北京市海淀区西二区中关村软件园","san_number":10,"bao_number":10}
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
         * imgurl : http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png
         * slids_img : ["http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png"]
         * recommend_img : ["http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/20180224951.png"]
         * everyone : 99
         * seat_number : 30
         * name : 驴肉火锅店北京分店
         * information : 正宗驴肉火锅店
         * telephone : 010-33256589
         * id : 1
         * position : 北京市海淀区西二区中关村软件园
         * san_number : 10
         * bao_number : 10
         */
        private String imgurl;
        private List<String> slids_img;
        private List<String> recommend_img;
        private int everyone;
        private String seat_number;
        private String name;
        private String information;
        private String telephone;
        private int id;
        private String position;
        private int san_number;
        private int bao_number;

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public void setSlids_img(List<String> slids_img) {
            this.slids_img = slids_img;
        }

        public void setRecommend_img(List<String> recommend_img) {
            this.recommend_img = recommend_img;
        }

        public void setEveryone(int everyone) {
            this.everyone = everyone;
        }

        public void setSeat_number(String seat_number) {
            this.seat_number = seat_number;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public void setSan_number(int san_number) {
            this.san_number = san_number;
        }

        public void setBao_number(int bao_number) {
            this.bao_number = bao_number;
        }

        public String getImgurl() {
            return imgurl;
        }

        public List<String> getSlids_img() {
            return slids_img;
        }

        public List<String> getRecommend_img() {
            return recommend_img;
        }

        public int getEveryone() {
            return everyone;
        }

        public String getSeat_number() {
            return seat_number;
        }

        public String getName() {
            return name;
        }

        public String getInformation() {
            return information;
        }

        public String getTelephone() {
            return telephone;
        }

        public int getId() {
            return id;
        }

        public String getPosition() {
            return position;
        }

        public int getSan_number() {
            return san_number;
        }

        public int getBao_number() {
            return bao_number;
        }
    }
}
