package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/20.
 */

public class RenYangDetailBean {


    /**
     * code : 1
     * data : {"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","distribution_eselsohr":10,"period":360,"amount":50,"introduce":"驴妈妈简介","birth_date":"2018-02-01","title":"第二期驴妈妈","hot":0,"profit_type":"到期一次性返本分红","content":"","details_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100063848.png","video_url":"","price":"10000.00","foster_time":1517477254,"rank":[{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/scx7RCtWySZ7W3ET1FE7.jpg","amount":38,"user_name":"波摸摸哦哦？这么多年一直坚持不下来呢！这","created_at":"2018.07.12 22:15:42"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/RlJqdYxISf3G4kUQe3d4.jpg","amount":1,"user_name":"三生三世","created_at":"2018.07.16 11:37:47"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201805/XJXvLFLDTV2BtkIGrFEL.jpg","amount":1,"user_name":"啊啊","created_at":"2018.07.12 14:32:18"}],"id":2,"place":"宁夏-青铜峡","state":2,"profit":15,"last_amount":48,"is_distribution":1,"buy_users":[{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/RlJqdYxISf3G4kUQe3d4.jpg","amount":1,"user_name":"三生三世","created_at":"2018.07.16 11:37:47"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/scx7RCtWySZ7W3ET1FE7.jpg","amount":38,"user_name":"波摸摸哦哦？这么多年一直坚持不下来呢！这","created_at":"2018.07.12 22:15:42"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201805/XJXvLFLDTV2BtkIGrFEL.jpg","amount":1,"user_name":"啊啊","created_at":"2018.07.12 14:32:18"}]}
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
         * image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png
         * distribution_eselsohr : 10
         * period : 360
         * amount : 50
         * introduce : 驴妈妈简介
         * birth_date : 2018-02-01
         * title : 第二期驴妈妈
         * hot : 0
         * profit_type : 到期一次性返本分红
         * content :
         * details_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100063848.png
         * video_url :
         * price : 10000.00
         * foster_time : 1517477254
         * rank : [{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/scx7RCtWySZ7W3ET1FE7.jpg","amount":38,"user_name":"波摸摸哦哦？这么多年一直坚持不下来呢！这","created_at":"2018.07.12 22:15:42"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/RlJqdYxISf3G4kUQe3d4.jpg","amount":1,"user_name":"三生三世","created_at":"2018.07.16 11:37:47"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201805/XJXvLFLDTV2BtkIGrFEL.jpg","amount":1,"user_name":"啊啊","created_at":"2018.07.12 14:32:18"}]
         * id : 2
         * place : 宁夏-青铜峡
         * state : 2
         * profit : 15
         * last_amount : 48
         * is_distribution : 1
         * buy_users : [{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/RlJqdYxISf3G4kUQe3d4.jpg","amount":1,"user_name":"三生三世","created_at":"2018.07.16 11:37:47"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/scx7RCtWySZ7W3ET1FE7.jpg","amount":38,"user_name":"波摸摸哦哦？这么多年一直坚持不下来呢！这","created_at":"2018.07.12 22:15:42"},{"user_avatar":"http://ssyd.oss-cn-beijing.aliyuncs.com/users/201805/XJXvLFLDTV2BtkIGrFEL.jpg","amount":1,"user_name":"啊啊","created_at":"2018.07.12 14:32:18"}]
         */
        private String image;
        private int distribution_eselsohr;
        private int period;
        private int amount;
        private String introduce;
        private String birth_date;
        private String title;
        private int hot;
        private String profit_type;
        private String content;
        private String details_image;
        private String video_url;
        private String price;
        private int foster_time;
        private List<RankEntity> rank;
        private int id;
        private String place;
        private int state;
        private int profit;
        private int last_amount;
        private int is_distribution;
        private List<BuyUsersEntity> buy_users;

        public void setImage(String image) {
            this.image = image;
        }

        public void setDistribution_eselsohr(int distribution_eselsohr) {
            this.distribution_eselsohr = distribution_eselsohr;
        }

        public void setPeriod(int period) {
            this.period = period;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public void setIntroduce(String introduce) {
            this.introduce = introduce;
        }

        public void setBirth_date(String birth_date) {
            this.birth_date = birth_date;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setHot(int hot) {
            this.hot = hot;
        }

        public void setProfit_type(String profit_type) {
            this.profit_type = profit_type;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setDetails_image(String details_image) {
            this.details_image = details_image;
        }

        public void setVideo_url(String video_url) {
            this.video_url = video_url;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public void setFoster_time(int foster_time) {
            this.foster_time = foster_time;
        }

        public void setRank(List<RankEntity> rank) {
            this.rank = rank;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPlace(String place) {
            this.place = place;
        }

        public void setState(int state) {
            this.state = state;
        }

        public void setProfit(int profit) {
            this.profit = profit;
        }

        public void setLast_amount(int last_amount) {
            this.last_amount = last_amount;
        }

        public void setIs_distribution(int is_distribution) {
            this.is_distribution = is_distribution;
        }

        public void setBuy_users(List<BuyUsersEntity> buy_users) {
            this.buy_users = buy_users;
        }

        public String getImage() {
            return image;
        }

        public int getDistribution_eselsohr() {
            return distribution_eselsohr;
        }

        public int getPeriod() {
            return period;
        }

        public int getAmount() {
            return amount;
        }

        public String getIntroduce() {
            return introduce;
        }

        public String getBirth_date() {
            return birth_date;
        }

        public String getTitle() {
            return title;
        }

        public int getHot() {
            return hot;
        }

        public String getProfit_type() {
            return profit_type;
        }

        public String getContent() {
            return content;
        }

        public String getDetails_image() {
            return details_image;
        }

        public String getVideo_url() {
            return video_url;
        }

        public String getPrice() {
            return price;
        }

        public int getFoster_time() {
            return foster_time;
        }

        public List<RankEntity> getRank() {
            return rank;
        }

        public int getId() {
            return id;
        }

        public String getPlace() {
            return place;
        }

        public int getState() {
            return state;
        }

        public int getProfit() {
            return profit;
        }

        public int getLast_amount() {
            return last_amount;
        }

        public int getIs_distribution() {
            return is_distribution;
        }

        public List<BuyUsersEntity> getBuy_users() {
            return buy_users;
        }

        public static class RankEntity {
            /**
             * user_avatar : http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/scx7RCtWySZ7W3ET1FE7.jpg
             * amount : 38
             * user_name : 波摸摸哦哦？这么多年一直坚持不下来呢！这
             * created_at : 2018.07.12 22:15:42
             */
            private String user_avatar;
            private int amount;
            private String user_name;
            private String created_at;

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public int getAmount() {
                return amount;
            }

            public String getUser_name() {
                return user_name;
            }

            public String getCreated_at() {
                return created_at;
            }
        }

        public static class BuyUsersEntity {
            /**
             * user_avatar : http://ssyd.oss-cn-beijing.aliyuncs.com/users/201807/RlJqdYxISf3G4kUQe3d4.jpg
             * amount : 1
             * user_name : 三生三世
             * created_at : 2018.07.16 11:37:47
             */
            private String user_avatar;
            private int amount;
            private String user_name;
            private String created_at;

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public int getAmount() {
                return amount;
            }

            public String getUser_name() {
                return user_name;
            }

            public String getCreated_at() {
                return created_at;
            }
        }
    }
}
