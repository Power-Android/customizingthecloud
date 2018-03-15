package com.power.customizingthecloud.fragment.home.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15.
 */

public class CanWeiListBean {

    /**
     * code : 1
     * data : {"slid":[{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":1},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":1}],"business":[{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","everyone":"人均消费低至100元","seat_number":"座位仅剩余30位","name":"驴肉火锅店北京分店3","id":3},{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","everyone":"人均消费低至88元","seat_number":"座位仅剩余30位","name":"驴肉火锅店北京分店2","id":2},{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","everyone":"人均消费低至99元","seat_number":"座位仅剩余30位","name":"驴肉火锅店北京分店","id":1}]}
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
         * slid : [{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":2,"state":1,"title":"测试轮播图","type":1},{"image_url":"http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png","targe_url":"1","id":1,"state":1,"title":"测试轮播图","type":1}]
         * business : [{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","everyone":"人均消费低至100元","seat_number":"座位仅剩余30位","name":"驴肉火锅店北京分店3","id":3},{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","everyone":"人均消费低至88元","seat_number":"座位仅剩余30位","name":"驴肉火锅店北京分店2","id":2},{"imgurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png","everyone":"人均消费低至99元","seat_number":"座位仅剩余30位","name":"驴肉火锅店北京分店","id":1}]
         */
        private List<SlidEntity> slid;
        private List<BusinessEntity> business;

        public void setSlid(List<SlidEntity> slid) {
            this.slid = slid;
        }

        public void setBusiness(List<BusinessEntity> business) {
            this.business = business;
        }

        public List<SlidEntity> getSlid() {
            return slid;
        }

        public List<BusinessEntity> getBusiness() {
            return business;
        }

        public static class SlidEntity {
            /**
             * image_url : http://ssyd.oss-cn-beijing.aliyuncs.com/guides/20180224123.png
             * targe_url : 1
             * id : 2
             * state : 1
             * title : 测试轮播图
             * type : 1
             */
            private String image_url;
            private String targe_url;
            private int id;
            private int state;
            private String title;
            private int type;

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

        public static class BusinessEntity {
            /**
             * imgurl : http://ssyd.oss-cn-beijing.aliyuncs.com/restaurants/2018022478965.png
             * everyone : 人均消费低至100元
             * seat_number : 座位仅剩余30位
             * name : 驴肉火锅店北京分店3
             * id : 3
             */
            private String imgurl;
            private String everyone;
            private String seat_number;
            private String name;
            private int id;

            public void setImgurl(String imgurl) {
                this.imgurl = imgurl;
            }

            public void setEveryone(String everyone) {
                this.everyone = everyone;
            }

            public void setSeat_number(String seat_number) {
                this.seat_number = seat_number;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getImgurl() {
                return imgurl;
            }

            public String getEveryone() {
                return everyone;
            }

            public String getSeat_number() {
                return seat_number;
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
