package com.power.customizingthecloud.fragment.market.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/3.
 */

public class MyDongTaiBean {

    /**
     * code : 1
     * data : {"feed":[{"images":[{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg","id":1,"feed_id":6,"height":"375.00"},{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/98746.jpg","id":2,"feed_id":6,"height":"383.00"}],"updated_at":"2018-03-07 15:33:30","user_id":1,"feed_content":"今天天气不错呀","created_at":"2018-03-07 15:33:30","id":6,"audit_status":1}],"user":{"user_avatar":"","user_name":"123","id":1}}
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
         * feed : [{"images":[{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg","id":1,"feed_id":6,"height":"375.00"},{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/98746.jpg","id":2,"feed_id":6,"height":"383.00"}],"updated_at":"2018-03-07 15:33:30","user_id":1,"feed_content":"今天天气不错呀","created_at":"2018-03-07 15:33:30","id":6,"audit_status":1}]
         * user : {"user_avatar":"","user_name":"123","id":1}
         */
        private List<FeedEntity> feed;
        private UserEntity user;

        public void setFeed(List<FeedEntity> feed) {
            this.feed = feed;
        }

        public void setUser(UserEntity user) {
            this.user = user;
        }

        public List<FeedEntity> getFeed() {
            return feed;
        }

        public UserEntity getUser() {
            return user;
        }

        public static class FeedEntity implements Serializable{
            /**
             * images : [{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg","id":1,"feed_id":6,"height":"375.00"},{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/98746.jpg","id":2,"feed_id":6,"height":"383.00"}]
             * updated_at : 2018-03-07 15:33:30
             * user_id : 1
             * feed_content : 今天天气不错呀
             * created_at : 2018-03-07 15:33:30
             * id : 6
             * audit_status : 1
             */
            private List<ImagesEntity> images;
            private String updated_at;
            private int user_id;
            private String feed_content;
            private String created_at;
            private int id;
            private int audit_status;

            public void setImages(List<ImagesEntity> images) {
                this.images = images;
            }

            public void setUpdated_at(String updated_at) {
                this.updated_at = updated_at;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public void setFeed_content(String feed_content) {
                this.feed_content = feed_content;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setAudit_status(int audit_status) {
                this.audit_status = audit_status;
            }

            public List<ImagesEntity> getImages() {
                return images;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public int getUser_id() {
                return user_id;
            }

            public String getFeed_content() {
                return feed_content;
            }

            public String getCreated_at() {
                return created_at;
            }

            public int getId() {
                return id;
            }

            public int getAudit_status() {
                return audit_status;
            }

            public static class ImagesEntity {
                /**
                 * width : 500.00
                 * fileurl : http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg
                 * id : 1
                 * feed_id : 6
                 * height : 375.00
                 */
                private String width;
                private String fileurl;
                private int id;
                private int feed_id;
                private String height;

                public void setWidth(String width) {
                    this.width = width;
                }

                public void setFileurl(String fileurl) {
                    this.fileurl = fileurl;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setFeed_id(int feed_id) {
                    this.feed_id = feed_id;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public String getFileurl() {
                    return fileurl;
                }

                public int getId() {
                    return id;
                }

                public int getFeed_id() {
                    return feed_id;
                }

                public String getHeight() {
                    return height;
                }
            }
        }

        public static class UserEntity {
            /**
             * user_avatar :
             * user_name : 123
             * id : 1
             */
            private String user_avatar;
            private String user_name;
            private int id;

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public String getUser_name() {
                return user_name;
            }

            public int getId() {
                return id;
            }
        }
    }
}
