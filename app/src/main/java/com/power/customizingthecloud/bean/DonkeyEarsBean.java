package com.power.customizingthecloud.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/1/23.
 */

public class DonkeyEarsBean implements Serializable {


    /**
     * code : 1
     * data : {"user_eselsohr":392,"good":[{"eselsohr_deduction":"￥10","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"￥100.00","name":"有机奶粉","id":1}],"is_sign":false}
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
         * user_eselsohr : 392
         * good : [{"eselsohr_deduction":"￥10","image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","price":"￥100.00","name":"有机奶粉","id":1}]
         * is_sign : false
         */
        private int user_eselsohr;
        private List<GoodEntity> good;
        private boolean is_sign;

        public void setUser_eselsohr(int user_eselsohr) {
            this.user_eselsohr = user_eselsohr;
        }

        public void setGood(List<GoodEntity> good) {
            this.good = good;
        }

        public void setIs_sign(boolean is_sign) {
            this.is_sign = is_sign;
        }

        public int getUser_eselsohr() {
            return user_eselsohr;
        }

        public List<GoodEntity> getGood() {
            return good;
        }

        public boolean isIs_sign() {
            return is_sign;
        }

        public static class GoodEntity {
            /**
             * eselsohr_deduction : ￥10
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * unit : 罐
             * price : ￥100.00
             * name : 有机奶粉
             * id : 1
             */
            private String eselsohr_deduction;
            private String image;
            private String unit;
            private String price;
            private String name;
            private int id;

            public void setEselsohr_deduction(String eselsohr_deduction) {
                this.eselsohr_deduction = eselsohr_deduction;
            }

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

            public String getEselsohr_deduction() {
                return eselsohr_deduction;
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
    }
}
