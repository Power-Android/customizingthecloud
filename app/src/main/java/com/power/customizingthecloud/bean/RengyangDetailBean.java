package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/2/23.
 */

public class RengyangDetailBean implements Serializable {

    /**
     * code : 1
     * message : ok
     * data : {"id":1,"title":"第一期驴妈妈","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","price":"10000.00","contract":"","amount":5,"place":"宁夏-青铜峡","birth_date":"2018-02-01","payment_time":"2018-02-28 17:57:24","video_url":"","donkey_images":["http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png"]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * title : 第一期驴妈妈
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png
         * price : 10000.00
         * contract :
         * amount : 5
         * place : 宁夏-青铜峡
         * birth_date : 2018-02-01
         * payment_time : 2018-02-28 17:57:24
         * video_url :
         * donkey_images : ["http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png"]
         */

        private int id;
        private String title;
        private String image;
        private String price;
        private String contract;
        private int amount;
        private String place;
        private String birth_date;
        private String payment_time;
        private String video_url;
        private List<String> donkey_images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getContract() {
            return contract;
        }

        public void setContract(String contract) {
            this.contract = contract;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getPlace() {
            return place;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public String getVideo_url() {
            return video_url;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public List<String> getDonkey_images() {
            return donkey_images;
        }

        public void setDonkey_images(List<String> donkey_images) {
            this.donkey_images = donkey_images;
        }
    }
}
