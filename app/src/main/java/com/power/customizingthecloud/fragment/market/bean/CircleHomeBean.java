package com.power.customizingthecloud.fragment.market.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/16.
 */

public class CircleHomeBean {

    /**
     * code : 1
     * data : {"feed":[{"user_avatar":"","images":[{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg","id":1,"feed_id":6,"height":"375.00"},{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/98746.jpg","id":2,"feed_id":6,"height":"383.00"}],"comments":[{"reply_name":"","reply_user":0,"user_id":11,"user_name":"潇洒","target_user":1,"id":1,"body":"非常不错","feed_id":6},{"reply_name":"潇洒","reply_user":11,"user_id":1,"user_name":"123","target_user":1,"id":2,"body":"支持你","feed_id":6}],"updated_at":"2018-03-07 15:33:30","user_id":1,"feed_content":"今天天气不错呀","user_name":"123","created_at":"2018-03-07 15:33:30","id":6,"audit_status":1,"likes":[{"user_id":1,"user_name":"123","target_user":1,"id":1,"feed_id":6}]}],"user":{"user_avatar":"","user_name":"123","id":1}}
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
         * feed : [{"user_avatar":"","images":[{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg","id":1,"feed_id":6,"height":"375.00"},{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/98746.jpg","id":2,"feed_id":6,"height":"383.00"}],"comments":[{"reply_name":"","reply_user":0,"user_id":11,"user_name":"潇洒","target_user":1,"id":1,"body":"非常不错","feed_id":6},{"reply_name":"潇洒","reply_user":11,"user_id":1,"user_name":"123","target_user":1,"id":2,"body":"支持你","feed_id":6}],"updated_at":"2018-03-07 15:33:30","user_id":1,"feed_content":"今天天气不错呀","user_name":"123","created_at":"2018-03-07 15:33:30","id":6,"audit_status":1,"likes":[{"user_id":1,"user_name":"123","target_user":1,"id":1,"feed_id":6}]}]
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

        public static class FeedEntity {
            /**
             * user_avatar :
             * images : [{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/1645646.jpg","id":1,"feed_id":6,"height":"375.00"},{"width":"500.00","fileurl":"http://ssyd.oss-cn-beijing.aliyuncs.com/feed/201803/98746.jpg","id":2,"feed_id":6,"height":"383.00"}]
             * comments : [{"reply_name":"","reply_user":0,"user_id":11,"user_name":"潇洒","target_user":1,"id":1,"body":"非常不错","feed_id":6},{"reply_name":"潇洒","reply_user":11,"user_id":1,"user_name":"123","target_user":1,"id":2,"body":"支持你","feed_id":6}]
             * updated_at : 2018-03-07 15:33:30
             * user_id : 1
             * feed_content : 今天天气不错呀
             * user_name : 123
             * created_at : 2018-03-07 15:33:30
             * id : 6
             * audit_status : 1
             * likes : [{"user_id":1,"user_name":"123","target_user":1,"id":1,"feed_id":6}]
             */
            private String user_avatar;
            private List<ImagesEntity> images;
            private List<CommentsEntity> comments;
            private String updated_at;
            private int user_id;
            private String feed_content;
            private String user_name;
            private String created_at;
            private int id;
            private int audit_status;
            private List<LikesEntity> likes;

            public void setUser_avatar(String user_avatar) {
                this.user_avatar = user_avatar;
            }

            public void setImages(List<ImagesEntity> images) {
                this.images = images;
            }

            public void setComments(List<CommentsEntity> comments) {
                this.comments = comments;
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

            public void setUser_name(String user_name) {
                this.user_name = user_name;
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

            public void setLikes(List<LikesEntity> likes) {
                this.likes = likes;
            }

            public String getUser_avatar() {
                return user_avatar;
            }

            public List<ImagesEntity> getImages() {
                return images;
            }

            public List<CommentsEntity> getComments() {
                return comments;
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

            public String getUser_name() {
                return user_name;
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

            public List<LikesEntity> getLikes() {
                return likes;
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

            public static class CommentsEntity {
                /**
                 * reply_name :
                 * reply_user : 0
                 * user_id : 11
                 * user_name : 潇洒
                 * target_user : 1
                 * id : 1
                 * body : 非常不错
                 * feed_id : 6
                 */
                private String reply_name;
                private int reply_user;
                private int user_id;
                private String user_name;
                private int target_user;
                private int id;
                private String body;
                private int feed_id;

                public void setReply_name(String reply_name) {
                    this.reply_name = reply_name;
                }

                public void setReply_user(int reply_user) {
                    this.reply_user = reply_user;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public void setTarget_user(int target_user) {
                    this.target_user = target_user;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setBody(String body) {
                    this.body = body;
                }

                public void setFeed_id(int feed_id) {
                    this.feed_id = feed_id;
                }

                public String getReply_name() {
                    return reply_name;
                }

                public int getReply_user() {
                    return reply_user;
                }

                public int getUser_id() {
                    return user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public int getTarget_user() {
                    return target_user;
                }

                public int getId() {
                    return id;
                }

                public String getBody() {
                    return body;
                }

                public int getFeed_id() {
                    return feed_id;
                }
            }

            public static class LikesEntity {
                /**
                 * user_id : 1
                 * user_name : 123
                 * target_user : 1
                 * id : 1
                 * feed_id : 6
                 */
                private int user_id;
                private String user_name;
                private int target_user;
                private int id;
                private int feed_id;

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public void setTarget_user(int target_user) {
                    this.target_user = target_user;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setFeed_id(int feed_id) {
                    this.feed_id = feed_id;
                }

                public int getUser_id() {
                    return user_id;
                }

                public String getUser_name() {
                    return user_name;
                }

                public int getTarget_user() {
                    return target_user;
                }

                public int getId() {
                    return id;
                }

                public int getFeed_id() {
                    return feed_id;
                }

                public LikesEntity(int user_id, String user_name) {
                    this.user_id = user_id;
                    this.user_name = user_name;
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
