package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/22.
 */

public class GoodDetailBean {

    /**
     * code : 1
     * data : {"eselsohr_deduction":10,"unit":"罐","images":[{"imag":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png"},{"imag":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png"}],"comments":[{"user_avatar":"","user_name":"123","time":"2018-03-21 00:00:00","content":"商品不错哦"}],"price":"100.00","name":"有机奶粉","id":1,"spec_value":["品牌：驴鲜生驴肉酱","贮存条件: 常温下保存","保质期：6个月","净含量：500kg","包装：灌装","生产许可证： QSXXXXX","产品标准号： GB/T 12345","包装清单：精制肥牛片540g×1"],"body":"特色有机奶粉","class_name":"休闲食品","good_storage":92}
     * message : ok
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
         * eselsohr_deduction : 10
         * unit : 罐
         * images : [{"imag":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png"},{"imag":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png"}]
         * comments : [{"user_avatar":"","user_name":"123","time":"2018-03-21 00:00:00","content":"商品不错哦"}]
         * price : 100.00
         * name : 有机奶粉
         * id : 1
         * spec_value : ["品牌：驴鲜生驴肉酱","贮存条件: 常温下保存","保质期：6个月","净含量：500kg","包装：灌装","生产许可证： QSXXXXX","产品标准号： GB/T 12345","包装清单：精制肥牛片540g×1"]
         * body : 特色有机奶粉
         * class_name : 休闲食品
         * good_storage : 92
         */
        private int eselsohr_deduction;
        private String unit;
        private List<ImagesEntity> images;
        private List<CommentsEntity> comments;
        private String price;
        private String name;
        private int id;
        private String spec_value;
        private String body;
        private String class_name;
        private int good_storage;

        public void setEselsohr_deduction(int eselsohr_deduction) {
            this.eselsohr_deduction = eselsohr_deduction;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setImages(List<ImagesEntity> images) {
            this.images = images;
        }

        public void setComments(List<CommentsEntity> comments) {
            this.comments = comments;
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

        public void setSpec_value(String spec_value) {
            this.spec_value = spec_value;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        public void setGood_storage(int good_storage) {
            this.good_storage = good_storage;
        }

        public int getEselsohr_deduction() {
            return eselsohr_deduction;
        }

        public String getUnit() {
            return unit;
        }

        public List<ImagesEntity> getImages() {
            return images;
        }

        public List<CommentsEntity> getComments() {
            return comments;
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

        public String getSpec_value() {
            return spec_value;
        }

        public String getBody() {
            return body;
        }

        public String getClass_name() {
            return class_name;
        }

        public int getGood_storage() {
            return good_storage;
        }

        public static class ImagesEntity {
            /**
             * imag : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png
             */
            private String imag;

            public void setImag(String imag) {
                this.imag = imag;
            }

            public String getImag() {
                return imag;
            }
        }

        public static class CommentsEntity {
            /**
             * user_avatar :
             * user_name : 123
             * time : 2018-03-21 00:00:00
             * content : 商品不错哦
             */
            private String user_avatar;
            private String user_name;
            private String time;
            private String content;

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public String getUser_name() {
                return user_name;
            }

            public String getTime() {
                return time;
            }

            public String getContent() {
                return content;
            }
        }
    }
}
