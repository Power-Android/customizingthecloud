package com.power.customizingthecloud.fragment.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class ShopAllBean {

    /**
     * code : 1
     * data : {"good_slid":[{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}],"hot_seckill":{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":20},"hot_good":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"200.00","name":"特色有机奶粉","good_type":"good","id":2},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"150.00","name":"驴肉后腿部","good_type":"good","id":4}],"seckill_good":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","seckill_end_time":1529375559,"price":"150.00","name":"特色有机奶粉","good_type":"seckill","seckill_price":"100.00","id":3,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"150.00","name":"驴肉后腿部","good_type":"seckill","seckill_price":"100.00","id":4,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":20}],"voucher_template":[{"end_date":"2018-09-19","price":20,"id":3,"state":1,"title":"奶粉代金券2","order_limit":40,"start_date":"2018-01-23"},{"end_date":"2018-09-19","price":30,"id":2,"state":1,"title":"奶粉代金券1","order_limit":50,"start_date":"2018-01-23"},{"end_date":"2018-09-19","price":10,"id":1,"state":1,"title":"奶粉代金券","order_limit":30,"start_date":"2018-01-23"}],"new_good":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"100.00","name":"有机奶粉","good_type":"good","id":1},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"150.00","name":"特色有机奶粉","good_type":"good","id":3}]}
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
         * good_slid : [{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":0},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/201801224654646.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":0}]
         * hot_seckill : {"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":20}
         * hot_good : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"200.00","name":"特色有机奶粉","good_type":"good","id":2},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"150.00","name":"驴肉后腿部","good_type":"good","id":4}]
         * seckill_good : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"罐","seckill_end_time":1529375559,"price":"150.00","name":"特色有机奶粉","good_type":"seckill","seckill_price":"100.00","id":3,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"150.00","name":"驴肉后腿部","good_type":"seckill","seckill_price":"100.00","id":4,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","unit":"斤","seckill_end_time":1529375559,"price":"120.00","name":"地方特色驴肉","good_type":"seckill","seckill_price":"100.00","id":5,"seckill_note":"","seckill_start_time":1516621657,"seckill_storage":20}]
         * voucher_template : [{"end_date":"2018-09-19","price":20,"id":3,"state":1,"title":"奶粉代金券2","order_limit":40,"start_date":"2018-01-23"},{"end_date":"2018-09-19","price":30,"id":2,"state":1,"title":"奶粉代金券1","order_limit":50,"start_date":"2018-01-23"},{"end_date":"2018-09-19","price":10,"id":1,"state":1,"title":"奶粉代金券","order_limit":30,"start_date":"2018-01-23"}]
         * new_good : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"100.00","name":"有机奶粉","good_type":"good","id":1},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","price":"150.00","name":"特色有机奶粉","good_type":"good","id":3}]
         */
        private List<GoodSlidEntity> good_slid;
        private HotSeckillEntity hot_seckill;
        private List<HotGoodEntity> hot_good;
        private List<SeckillGoodEntity> seckill_good;
        private List<VoucherTemplateEntity> voucher_template;
        private List<NewGoodEntity> new_good;

        public void setGood_slid(List<GoodSlidEntity> good_slid) {
            this.good_slid = good_slid;
        }

        public void setHot_seckill(HotSeckillEntity hot_seckill) {
            this.hot_seckill = hot_seckill;
        }

        public void setHot_good(List<HotGoodEntity> hot_good) {
            this.hot_good = hot_good;
        }

        public void setSeckill_good(List<SeckillGoodEntity> seckill_good) {
            this.seckill_good = seckill_good;
        }

        public void setVoucher_template(List<VoucherTemplateEntity> voucher_template) {
            this.voucher_template = voucher_template;
        }

        public void setNew_good(List<NewGoodEntity> new_good) {
            this.new_good = new_good;
        }

        public List<GoodSlidEntity> getGood_slid() {
            return good_slid;
        }

        public HotSeckillEntity getHot_seckill() {
            return hot_seckill;
        }

        public List<HotGoodEntity> getHot_good() {
            return hot_good;
        }

        public List<SeckillGoodEntity> getSeckill_good() {
            return seckill_good;
        }

        public List<VoucherTemplateEntity> getVoucher_template() {
            return voucher_template;
        }

        public List<NewGoodEntity> getNew_good() {
            return new_good;
        }

        public static class GoodSlidEntity {
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
            private int targe_type;

            public int getTarge_type() {
                return targe_type;
            }

            public void setTarge_type(int targe_type) {
                this.targe_type = targe_type;
            }

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

        public static class HotSeckillEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * unit : 斤
             * seckill_end_time : 1529375559
             * price : 120.00
             * name : 地方特色驴肉
             * good_type : seckill
             * seckill_price : 100.00
             * id : 5
             * seckill_note :
             * seckill_start_time : 1516621657
             * seckill_storage : 20
             */
            private String image;
            private String unit;
            private int seckill_end_time;
            private String price;
            private String name;
            private String good_type;
            private String seckill_price;
            private int id;
            private String seckill_note;
            private int seckill_start_time;
            private int seckill_storage;

            public void setImage(String image) {
                this.image = image;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setSeckill_end_time(int seckill_end_time) {
                this.seckill_end_time = seckill_end_time;
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

            public void setSeckill_price(String seckill_price) {
                this.seckill_price = seckill_price;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setSeckill_note(String seckill_note) {
                this.seckill_note = seckill_note;
            }

            public void setSeckill_start_time(int seckill_start_time) {
                this.seckill_start_time = seckill_start_time;
            }

            public void setSeckill_storage(int seckill_storage) {
                this.seckill_storage = seckill_storage;
            }

            public String getImage() {
                return image;
            }

            public String getUnit() {
                return unit;
            }

            public int getSeckill_end_time() {
                return seckill_end_time;
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

            public String getSeckill_price() {
                return seckill_price;
            }

            public int getId() {
                return id;
            }

            public String getSeckill_note() {
                return seckill_note;
            }

            public int getSeckill_start_time() {
                return seckill_start_time;
            }

            public int getSeckill_storage() {
                return seckill_storage;
            }
        }

        public static class HotGoodEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * price : 200.00
             * name : 特色有机奶粉
             * good_type : good
             * id : 2
             */
            private String image;
            private String price;
            private String name;
            private String good_type;
            private int id;

            public void setImage(String image) {
                this.image = image;
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

        public static class SeckillGoodEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * unit : 罐
             * seckill_end_time : 1529375559
             * price : 150.00
             * name : 特色有机奶粉
             * good_type : seckill
             * seckill_price : 100.00
             * id : 3
             * seckill_note :
             * seckill_start_time : 1516621657
             * seckill_storage : 30
             */
            private String image;
            private String unit;
            private int seckill_end_time;
            private String price;
            private String name;
            private String good_type;
            private String seckill_price;
            private int id;
            private String seckill_note;
            private int seckill_start_time;
            private int seckill_storage;

            public void setImage(String image) {
                this.image = image;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setSeckill_end_time(int seckill_end_time) {
                this.seckill_end_time = seckill_end_time;
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

            public void setSeckill_price(String seckill_price) {
                this.seckill_price = seckill_price;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setSeckill_note(String seckill_note) {
                this.seckill_note = seckill_note;
            }

            public void setSeckill_start_time(int seckill_start_time) {
                this.seckill_start_time = seckill_start_time;
            }

            public void setSeckill_storage(int seckill_storage) {
                this.seckill_storage = seckill_storage;
            }

            public String getImage() {
                return image;
            }

            public String getUnit() {
                return unit;
            }

            public int getSeckill_end_time() {
                return seckill_end_time;
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

            public String getSeckill_price() {
                return seckill_price;
            }

            public int getId() {
                return id;
            }

            public String getSeckill_note() {
                return seckill_note;
            }

            public int getSeckill_start_time() {
                return seckill_start_time;
            }

            public int getSeckill_storage() {
                return seckill_storage;
            }
        }

        public static class VoucherTemplateEntity {
            /**
             * end_date : 2018-09-19
             * price : 20
             * id : 3
             * state : 1
             * title : 奶粉代金券2
             * order_limit : 40
             * start_date : 2018-01-23
             */
            private String end_date;
            private int price;
            private int id;
            private int state;
            private String title;
            private int order_limit;
            private String start_date;
            private String describe;

            public String getDescribe() {
                return describe;
            }

            public void setDescribe(String describe) {
                this.describe = describe;
            }

            public void setEnd_date(String end_date) {
                this.end_date = end_date;
            }

            public void setPrice(int price) {
                this.price = price;
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

            public void setOrder_limit(int order_limit) {
                this.order_limit = order_limit;
            }

            public void setStart_date(String start_date) {
                this.start_date = start_date;
            }

            public String getEnd_date() {
                return end_date;
            }

            public int getPrice() {
                return price;
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

            public int getOrder_limit() {
                return order_limit;
            }

            public String getStart_date() {
                return start_date;
            }
        }

        public static class NewGoodEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * price : 100.00
             * name : 有机奶粉
             * good_type : good
             * id : 1
             */
            private String image;
            private String price;
            private String name;
            private String good_type;
            private int id;

            public void setImage(String image) {
                this.image = image;
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
    }
}
