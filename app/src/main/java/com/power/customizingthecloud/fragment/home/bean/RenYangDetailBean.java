package com.power.customizingthecloud.fragment.home.bean;

/**
 * Created by Administrator on 2018/3/20.
 */

public class RenYangDetailBean {

    /**
     * code : 1
     * data : {"image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100229239.png","distribution_eselsohr":0,"period":360,"amount":50,"introduce":"驴妈妈简介","title":"第一期驴妈妈","hot":0,"profit_type":"到期一次性返本分红","content":"","details_image":"http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100063848.png","video_url":"","price":"10000.00","foster_time":1517477254,"id":1,"place":"宁夏-青铜峡","state":3,"profit":15,"last_amount":50,"is_distribution":0}
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
         * distribution_eselsohr : 0
         * period : 360
         * amount : 50
         * introduce : 驴妈妈简介
         * title : 第一期驴妈妈
         * hot : 0
         * profit_type : 到期一次性返本分红
         * content :
         * details_image : http://ssyd.oss-cn-beijing.aliyuncs.com/goods/201801/100063848.png
         * video_url :
         * price : 10000.00
         * foster_time : 1517477254
         * id : 1
         * place : 宁夏-青铜峡
         * state : 3
         * profit : 15
         * last_amount : 50
         * is_distribution : 0
         */
        private String image;
        private int distribution_eselsohr;
        private int period;
        private int amount;
        private String introduce;
        private String title;
        private int hot;
        private String profit_type;
        private String content;
        private String details_image;
        private String video_url;
        private String price;
        private int foster_time;
        private int id;
        private String place;
        private int state;
        private int profit;
        private int last_amount;
        private int is_distribution;

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
    }
}
