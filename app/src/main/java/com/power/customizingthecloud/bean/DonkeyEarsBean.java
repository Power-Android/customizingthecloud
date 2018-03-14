package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class DonkeyEarsBean implements Serializable {

    /**
     * code : 1
     * data : {"good":[{"eselsohr_deduction":"￥10","id":1,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","name":"有机奶粉","price":"￥100.00","unit":"罐"}],"user_eselsohr":0}
     * message : ok
     */

    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * good : [{"eselsohr_deduction":"￥10","id":1,"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","name":"有机奶粉","price":"￥100.00","unit":"罐"}]
         * user_eselsohr : 0
         */

        private int user_eselsohr;
        private List<GoodBean> good;

        public int getUser_eselsohr() {
            return user_eselsohr;
        }

        public void setUser_eselsohr(int user_eselsohr) {
            this.user_eselsohr = user_eselsohr;
        }

        public List<GoodBean> getGood() {
            return good;
        }

        public void setGood(List<GoodBean> good) {
            this.good = good;
        }

        public static class GoodBean {
            /**
             * eselsohr_deduction : ￥10
             * id : 1
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * name : 有机奶粉
             * price : ￥100.00
             * unit : 罐
             */

            private String eselsohr_deduction;
            private int id;
            private String image;
            private String name;
            private String price;
            private String unit;

            public String getEselsohr_deduction() {
                return eselsohr_deduction;
            }

            public void setEselsohr_deduction(String eselsohr_deduction) {
                this.eselsohr_deduction = eselsohr_deduction;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }
        }
    }
}
