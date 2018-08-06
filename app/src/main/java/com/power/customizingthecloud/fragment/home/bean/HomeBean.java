package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/14.
 */

public class HomeBean {

    /**
     * code : 1
     * data : {"homeslid":[{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图"},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图"}],"grab_donkey":{"period":"360天","price":"10000.00","profit":"15%","last_amount":"仅剩余：50头"},"seckill_good":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","seckill_end_time":1529375559,"unit":"罐","price":"150.00","good_type":"seckill","name":"特色有机奶粉","seckill_price":"100.00","id":3,"seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","seckill_end_time":1529375559,"unit":"斤","price":"150.00","good_type":"seckill","name":"驴肉后腿部","seckill_price":"100.00","id":4,"seckill_start_time":1516621657,"seckill_storage":30}],"donkey":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","amount":"50头","period":"360天","video_url":"","price":"10000.00","state":1,"title":"第三期驴妈妈","profit":"15%","last_amount":"50头"}],"hot_goods":[{"good_type":"good","hot_imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/20180309161025.png","id":2},{"good_type":"good","hot_imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/20180309161025.png","id":4}],"toutiao":{"id":1,"state":1,"content":"APP上线啦,请大家多多关注"},"muchang":[{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","id":2,"title":"毛驴运动B区"},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","id":1,"title":"毛驴运动A区"}]}
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
         * homeslid : [{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图"},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图"}]
         * grab_donkey : {"period":"360天","price":"10000.00","profit":"15%","last_amount":"仅剩余：50头"}
         * seckill_good : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","seckill_end_time":1529375559,"unit":"罐","price":"150.00","good_type":"seckill","name":"特色有机奶粉","seckill_price":"100.00","id":3,"seckill_start_time":1516621657,"seckill_storage":30},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png","seckill_end_time":1529375559,"unit":"斤","price":"150.00","good_type":"seckill","name":"驴肉后腿部","seckill_price":"100.00","id":4,"seckill_start_time":1516621657,"seckill_storage":30}]
         * donkey : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","amount":"50头","period":"360天","video_url":"","price":"10000.00","state":1,"title":"第三期驴妈妈","profit":"15%","last_amount":"50头"}]
         * hot_goods : [{"good_type":"good","hot_imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/20180309161025.png","id":2},{"good_type":"good","hot_imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/20180309161025.png","id":4}]
         * toutiao : {"id":1,"state":1,"content":"APP上线啦,请大家多多关注"}
         * muchang : [{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","id":2,"title":"毛驴运动B区"},{"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png","id":1,"title":"毛驴运动A区"}]
         */
        private List<HomeslidEntity> homeslid;
        private GrabDonkeyEntity grab_donkey;
        private List<SeckillGoodEntity> seckill_good;
        private List<DonkeyEntity> donkey;
        private List<HotGoodsEntity> hot_goods;
        private ToutiaoEntity toutiao;
        private List<MuchangEntity> muchang;

        public void setHomeslid(List<HomeslidEntity> homeslid) {
            this.homeslid = homeslid;
        }

        public void setGrab_donkey(GrabDonkeyEntity grab_donkey) {
            this.grab_donkey = grab_donkey;
        }

        public void setSeckill_good(List<SeckillGoodEntity> seckill_good) {
            this.seckill_good = seckill_good;
        }

        public void setDonkey(List<DonkeyEntity> donkey) {
            this.donkey = donkey;
        }

        public void setHot_goods(List<HotGoodsEntity> hot_goods) {
            this.hot_goods = hot_goods;
        }

        public void setToutiao(ToutiaoEntity toutiao) {
            this.toutiao = toutiao;
        }

        public void setMuchang(List<MuchangEntity> muchang) {
            this.muchang = muchang;
        }

        public List<HomeslidEntity> getHomeslid() {
            return homeslid;
        }

        public GrabDonkeyEntity getGrab_donkey() {
            return grab_donkey;
        }

        public List<SeckillGoodEntity> getSeckill_good() {
            return seckill_good;
        }

        public List<DonkeyEntity> getDonkey() {
            return donkey;
        }

        public List<HotGoodsEntity> getHot_goods() {
            return hot_goods;
        }

        public ToutiaoEntity getToutiao() {
            return toutiao;
        }

        public List<MuchangEntity> getMuchang() {
            return muchang;
        }

        public static class HomeslidEntity {
            /**
             * image_url : http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png
             * targe_url : 1
             * id : 2
             * state : 1
             * title : 测试轮播图
             */
            private String image_url;
            private String targe_url;
            private int id;
            private int state;
            private int targe_type;
            private String title;

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
        }

        public static class GrabDonkeyEntity {
            /**
             * period : 360天
             * price : 10000.00
             * profit : 15%
             * last_amount : 仅剩余：50头
             */
            private String period;
            private String price;
            private String profit;
            private String last_amount;

            public void setPeriod(String period) {
                this.period = period;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setProfit(String profit) {
                this.profit = profit;
            }

            public void setLast_amount(String last_amount) {
                this.last_amount = last_amount;
            }

            public String getPeriod() {
                return period;
            }

            public String getPrice() {
                return price;
            }

            public String getProfit() {
                return profit;
            }

            public String getLast_amount() {
                return last_amount;
            }
        }

        public static class SeckillGoodEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/2601513049970.png
             * seckill_end_time : 1529375559
             * unit : 罐
             * price : 150.00
             * good_type : seckill
             * name : 特色有机奶粉
             * seckill_price : 100.00
             * id : 3
             * seckill_start_time : 1516621657
             * seckill_storage : 30
             */
            private String image;
            private int seckill_end_time;
            private String unit;
            private String price;
            private String good_type;
            private String name;
            private String seckill_price;
            private int id;
            private int seckill_start_time;
            private int seckill_storage;

            public void setImage(String image) {
                this.image = image;
            }

            public void setSeckill_end_time(int seckill_end_time) {
                this.seckill_end_time = seckill_end_time;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setGood_type(String good_type) {
                this.good_type = good_type;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setSeckill_price(String seckill_price) {
                this.seckill_price = seckill_price;
            }

            public void setId(int id) {
                this.id = id;
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

            public int getSeckill_end_time() {
                return seckill_end_time;
            }

            public String getUnit() {
                return unit;
            }

            public String getPrice() {
                return price;
            }

            public String getGood_type() {
                return good_type;
            }

            public String getName() {
                return name;
            }

            public String getSeckill_price() {
                return seckill_price;
            }

            public int getId() {
                return id;
            }

            public int getSeckill_start_time() {
                return seckill_start_time;
            }

            public int getSeckill_storage() {
                return seckill_storage;
            }
        }

        public static class DonkeyEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png
             * amount : 50头
             * period : 360天
             * video_url :
             * price : 10000.00
             * state : 1
             * title : 第三期驴妈妈
             * profit : 15%
             * last_amount : 50头
             */
            private String image;
            private String amount;
            private String period;
            private String video_url;
            private String price;
            private int state;
            private String title;
            private String profit;
            private String last_amount;
            private int id;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public void setPeriod(String period) {
                this.period = period;
            }

            public void setVideo_url(String video_url) {
                this.video_url = video_url;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setState(int state) {
                this.state = state;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setProfit(String profit) {
                this.profit = profit;
            }

            public void setLast_amount(String last_amount) {
                this.last_amount = last_amount;
            }

            public String getImage() {
                return image;
            }

            public String getAmount() {
                return amount;
            }

            public String getPeriod() {
                return period;
            }

            public String getVideo_url() {
                return video_url;
            }

            public String getPrice() {
                return price;
            }

            public int getState() {
                return state;
            }

            public String getTitle() {
                return title;
            }

            public String getProfit() {
                return profit;
            }

            public String getLast_amount() {
                return last_amount;
            }
        }

        public static class HotGoodsEntity {
            /**
             * good_type : good
             * hot_imgurl : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/20180309161025.png
             * id : 2
             */
            private String good_type;
            private String hot_imgurl;
            private int id;

            public void setGood_type(String good_type) {
                this.good_type = good_type;
            }

            public void setHot_imgurl(String hot_imgurl) {
                this.hot_imgurl = hot_imgurl;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getGood_type() {
                return good_type;
            }

            public String getHot_imgurl() {
                return hot_imgurl;
            }

            public int getId() {
                return id;
            }
        }

        public static class ToutiaoEntity {
            /**
             * id : 1
             * state : 1
             * content : APP上线啦,请大家多多关注
             */
            private int id;
            private int state;
            private String content;

            public void setId(int id) {
                this.id = id;
            }

            public void setState(int state) {
                this.state = state;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public int getState() {
                return state;
            }

            public String getContent() {
                return content;
            }
        }

        public static class MuchangEntity {
            /**
             * image : http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/201802/20180227753.png
             * id : 2
             * title : 毛驴运动B区
             */
            private String image;
            private int id;
            private String title;

            public void setImage(String image) {
                this.image = image;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImage() {
                return image;
            }

            public int getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }
        }
    }
}
