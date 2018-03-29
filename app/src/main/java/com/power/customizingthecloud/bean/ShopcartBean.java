package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class ShopcartBean implements Serializable {

    /**
     * code : 1
     * message : 请求成功
     * data : [{"id":1,"buy_user_id":1,"good_id":1,"class_name":"休闲食品","good_name":"有机奶粉","good_price":"100.00","good_unit":null,"good_num":2,"good_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","good_type":1,"state":true,"storage_state":true,"buy_type":1,"is_distribution":0,"distribution_price":0,"eselsohr_deduction":10,"is_voucher":1,"is_seckill":0,"seckill_note":"","seckill_price":"0.00","seckill_storage":0,"seckill_start_time":0,"seckill_end_time":0}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * buy_user_id : 1
         * good_id : 1
         * class_name : 休闲食品
         * good_name : 有机奶粉
         * good_price : 100.00
         * good_unit : null
         * good_num : 2
         * good_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
         * good_type : 1
         * state : true
         * storage_state : true
         * buy_type : 1
         * is_distribution : 0
         * distribution_price : 0
         * eselsohr_deduction : 10
         * is_voucher : 1
         * is_seckill : 0
         * seckill_note :
         * seckill_price : 0.00
         * seckill_storage : 0
         * seckill_start_time : 0
         * seckill_end_time : 0
         */

        private int id;
        private int buy_user_id;
        private int good_id;
        private String class_name;
        private String good_name;
        private float good_price;
        private Object good_unit;
        private int good_num;
        private String good_image;
        private int good_type;
        private boolean state;
        private boolean storage_state;
        private int buy_type;
        private int is_distribution;
        private int distribution_price;
        private int eselsohr_deduction;
        private int is_voucher;
        private int is_seckill;
        private String seckill_note;
        private String seckill_price;
        private int seckill_storage;
        private int seckill_start_time;
        private int seckill_end_time;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getBuy_user_id() {
            return buy_user_id;
        }

        public void setBuy_user_id(int buy_user_id) {
            this.buy_user_id = buy_user_id;
        }

        public int getGood_id() {
            return good_id;
        }

        public void setGood_id(int good_id) {
            this.good_id = good_id;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public String getGood_name() {
            return good_name;
        }

        public void setGood_name(String good_name) {
            this.good_name = good_name;
        }

        public float getGood_price() {
            return good_price;
        }

        public void setGood_price(float good_price) {
            this.good_price = good_price;
        }

        public Object getGood_unit() {
            return good_unit;
        }

        public void setGood_unit(Object good_unit) {
            this.good_unit = good_unit;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public String getGood_image() {
            return good_image;
        }

        public void setGood_image(String good_image) {
            this.good_image = good_image;
        }

        public int getGood_type() {
            return good_type;
        }

        public void setGood_type(int good_type) {
            this.good_type = good_type;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public boolean isStorage_state() {
            return storage_state;
        }

        public void setStorage_state(boolean storage_state) {
            this.storage_state = storage_state;
        }

        public int getBuy_type() {
            return buy_type;
        }

        public void setBuy_type(int buy_type) {
            this.buy_type = buy_type;
        }

        public int getIs_distribution() {
            return is_distribution;
        }

        public void setIs_distribution(int is_distribution) {
            this.is_distribution = is_distribution;
        }

        public int getDistribution_price() {
            return distribution_price;
        }

        public void setDistribution_price(int distribution_price) {
            this.distribution_price = distribution_price;
        }

        public int getEselsohr_deduction() {
            return eselsohr_deduction;
        }

        public void setEselsohr_deduction(int eselsohr_deduction) {
            this.eselsohr_deduction = eselsohr_deduction;
        }

        public int getIs_voucher() {
            return is_voucher;
        }

        public void setIs_voucher(int is_voucher) {
            this.is_voucher = is_voucher;
        }

        public int getIs_seckill() {
            return is_seckill;
        }

        public void setIs_seckill(int is_seckill) {
            this.is_seckill = is_seckill;
        }

        public String getSeckill_note() {
            return seckill_note;
        }

        public void setSeckill_note(String seckill_note) {
            this.seckill_note = seckill_note;
        }

        public String getSeckill_price() {
            return seckill_price;
        }

        public void setSeckill_price(String seckill_price) {
            this.seckill_price = seckill_price;
        }

        public int getSeckill_storage() {
            return seckill_storage;
        }

        public void setSeckill_storage(int seckill_storage) {
            this.seckill_storage = seckill_storage;
        }

        public int getSeckill_start_time() {
            return seckill_start_time;
        }

        public void setSeckill_start_time(int seckill_start_time) {
            this.seckill_start_time = seckill_start_time;
        }

        public int getSeckill_end_time() {
            return seckill_end_time;
        }

        public void setSeckill_end_time(int seckill_end_time) {
            this.seckill_end_time = seckill_end_time;
        }
    }
}
